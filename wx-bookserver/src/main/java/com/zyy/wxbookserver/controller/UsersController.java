package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.model.Orders;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.service.IUsersService;
import com.zyy.wxbookserver.util.ApiConstants;
import com.zyy.wxbookserver.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/api/user")
public class UsersController extends BaseController {

    @Resource
    private IUsersService usersService;

    @ResponseBody
    @RequestMapping(value = "/getBoughtBooks")
    public Map<String, Object> buy(String skey) {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> list = usersService.getUserBuyBooks(skey);
        data.put("list", list);
        return super.getReturnData(data, ApiConstants.Result.SUCCESS);
    }
}
