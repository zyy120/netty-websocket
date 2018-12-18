package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.config.WxPayProperties;
import com.zyy.wxbookserver.util.CacheConstan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private WxPayProperties wxPayProperties;
    @RequestMapping("/hello")
    private String index(){
        redisTemplate.opsForValue().set(CacheConstan.BOOK_LIST+"1001","zyy",600,TimeUnit.HOURS);
       String y= (String) redisTemplate.opsForValue().get("a");
        System.out.println(y);
        System.out.println(wxPayProperties.getAppId());
        return "Hello World!";
    }
}
