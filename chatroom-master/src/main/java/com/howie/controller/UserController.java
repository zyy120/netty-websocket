package com.howie.controller;


import com.howie.model.TUser;
import com.howie.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUser.do" )
    public TUser addUser(int id){
        return userService.selectByPrimaryKey(Integer.valueOf(id));
    }
}
