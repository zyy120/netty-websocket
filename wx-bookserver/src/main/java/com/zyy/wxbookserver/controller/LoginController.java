package com.zyy.wxbookserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.google.gson.Gson;
import com.zyy.wxbookserver.config.WxPayProperties;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.model.WeChatSession;
import com.zyy.wxbookserver.service.IUsersService;
import com.zyy.wxbookserver.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class LoginController extends BaseController {

    private static final Logger log=Logger.getLogger(LoginController.class);


    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private IUsersService usersService;

    /**
     * @param
     * @Description: 方法说明      登录接口
     *  code: loginRes.code,                    // 临时登录凭证
     *  rawData: infoRes.rawData,               // 用户非敏感信息
     *  signature: infoRes.signature,           // 签名
     *  encryptedData: infoRes.encryptedData,   // 用户敏感信息
     *  iv: infoRes.iv                          // 解密算法的向量
     * @Author: zyy
     * @date: 2018/12/18 17:30
     */
    @RequestMapping("/login")
    private Map<String,Object> login(String code,String rawData,String signature ,String encryptedData,String iv){
        Map<String,Object> result=new HashMap<>();
        log.info(":code:"+code+":rawData:"+rawData+":signature:"+signature+":encryptedData:"+encryptedData+":iv:"+iv);
        //微信那边的接口，grant_type=authorization_code是固定的
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+wxPayProperties.getAppId()+
                "&secret="+wxPayProperties.getMchKey()+"&js_code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
        {
            String sessionData = responseEntity.getBody();
            log.info("wx url http responsebody ========"+sessionData);
            Gson gson = new Gson();
            //解析从微信服务器获得的openid和session_key;
            WeChatSession weChatSession = gson.fromJson(sessionData,WeChatSession.class);
            //获取用户的唯一标识
            String openid = weChatSession.getOpenid();
            //获取会话秘钥
            String sessionKey = weChatSession.getSession_key();

            log.info("openid"+openid+":sessionKey:"+sessionKey);
            result.put("openid",openid);
            result.put("sessionKey",sessionKey);

            Users u=  usersService.selectByUid(openid);
            Users users=new Users();
            JSONObject jsonUser=JSONObject.parseObject(rawData);
            //uuid生成唯一key
            String skey = UUID.randomUUID().toString();
            if (Objects.isNull(u)) {
                users.setUpdateTime(new Date());
                users.setUname(jsonUser.getString("nickName"));
                users.setUaddress(jsonUser.getString("province")+jsonUser.getString("city"));
                users.setSessionkey(sessionKey);
                users.setSkey(skey);
                users.setCreateTime(new Date());
                users.setUavatar(jsonUser.getString("avatarUrl"));
                users.setUgender(jsonUser.getInteger("gender"));
                users.setUid(openid);
                usersService.insertSelective(users);
            } else {//用户存在
                u.setUavatar(jsonUser.getString("avatarUrl"));
                u.setUpdateTime(new Date());
                users.setSkey(skey);
                usersService.updateByPrimaryKeySelective(u);
            }
            String skey_redis = (String) redisTemplate.opsForValue().get( openid );
            if(StringUtils.isNotBlank( skey_redis )){
                //存在 删除 skey 重新生成skey 将skey返回
                redisTemplate.delete( skey_redis );
            }
            //  缓存一份新的
            JSONObject sessionObj = new JSONObject(  );
            sessionObj.put( "openId",openid );
            sessionObj.put( "sessionKey",sessionKey );
            redisTemplate.opsForValue().set( skey,sessionObj.toJSONString() );
            redisTemplate.opsForValue().set( openid,skey );

            //把新的sessionKey和oppenid返回给小程序
            result.put("skey", skey);
            JSONObject userInfos = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            userInfos.put( "balance",users.getUbalance() );
            result.put("userInfo", userInfos);
            log.info(userInfos);
        }
     return  super.getReturnData(result, ApiConstants.Result.SUCCESS);
    }
}
