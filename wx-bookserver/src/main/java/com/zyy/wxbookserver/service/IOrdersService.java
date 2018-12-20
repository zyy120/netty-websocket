package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Orders;

import java.util.Map;

public interface IOrdersService {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int getOrderyBuyCount(Map<String,Object> param);

    /**
     * @param
     * @Description: 方法说明 书籍兑换
     * @Author: zyy
     * @date: 2018/12/20 15:30
     */
    int  addBookOrder(Orders orders,int ubalance);

    /**
     * @param
     * @Description: 方法说明 检查用户是否已经购买书本
     * @Author: zyy
     * @date: 2018/12/20 16:21
     */
    Orders checkUserBuyBook (Orders orders);
}
