package com.howie.controller;


import com.howie.model.TUser;
import com.howie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping(value = "/getUser.do" )
    public TUser addUser(int id){
        redisTemplate.opsForValue().set("setkey","33sdfsdfs11",1,TimeUnit.DAYS);
        String s=  redisTemplate.opsForValue().get("setkey");
        System.out.println(s);
        return userService.selectByPrimaryKey(Integer.valueOf(id));
    }
}
