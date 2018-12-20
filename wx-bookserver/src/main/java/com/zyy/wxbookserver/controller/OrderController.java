package com.zyy.wxbookserver.controller;


import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.model.Orders;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.service.IBooksService;
import com.zyy.wxbookserver.service.IOrdersService;
import com.zyy.wxbookserver.service.IUsersService;
import com.zyy.wxbookserver.util.ApiConstants;
import com.zyy.wxbookserver.util.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    private static final Logger log=Logger.getLogger(OrderController.class);

    @Resource
    private IOrdersService ordersService;

    @Resource
    private IBooksService booksService;

    @Resource
    private IUsersService usersService;

    /**
     * @param
     * @Description: 方法说明 积分兑换书本
     * @Author: zyy
     * @date: 2018/12/20 15:02
     */
    @ResponseBody
    @RequestMapping(value = "/buy" )
    public Map<String,Object> buy(String skey,int bookid){
        Map<String,Object> data=new HashMap<>();
        Map<String, Object> param =new HashMap<>();

       Books books= booksService.selectByPrimaryKey(bookid);
       if(Objects.isNull(books)) {
           return super.getReturnError(ApiConstants.Result.FAILURE_1003, "书籍信息出错");
       }
        int price=books.getBkprice();
        Users users = usersService.selectBySkey(skey);
        if(users.getUbalance() < price) {
            return super.getReturnError(ApiConstants.Result.FAILURE_1004, "余额不足，无法购买");
        }
        param.put("skey",skey);
        param.put("bookid",bookid);
        Orders orders=new Orders();
        orders.setUid(users.getUid());
        orders.setBkid(bookid);
        Orders userOrder=ordersService.checkUserBuyBook(orders);
        if(Objects.nonNull(userOrder)){
            return super.getReturnError(ApiConstants.Result.FAILURE_1006, "您已经购买过该书");
        }
        orders.setOtime(new Date());
        orders.setOprice(books.getBkprice());
        int count=ordersService.addBookOrder(orders,users.getUbalance()-price);
        if(count >0){
            return super.getReturnError(ApiConstants.Result.SUCCESS, "兑换成功");
        }
        return super.getReturnError(ApiConstants.Result.FAILURE_1005, "兑换失败");
    }
}
