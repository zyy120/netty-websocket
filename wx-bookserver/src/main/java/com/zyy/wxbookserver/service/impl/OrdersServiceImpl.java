package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.OrdersMapper;
import com.zyy.wxbookserver.mapper.UsersMapper;
import com.zyy.wxbookserver.model.Orders;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.service.IOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private UsersMapper usersMapper;
    @Override
    public int deleteByPrimaryKey(Integer oid) {
        return ordersMapper.deleteByPrimaryKey(oid);
    }

    @Override
    public int insert(Orders record) {
        return ordersMapper.insert(record);
    }

    @Override
    public int insertSelective(Orders record) {
        return ordersMapper.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(Integer oid) {
        return ordersMapper.selectByPrimaryKey(oid);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return ordersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return ordersMapper.updateByPrimaryKey(record);
    }

    @Override
    public int getOrderyBuyCount(Map<String, Object> param) {
        return ordersMapper.getOrderyBuyCount(param);
    }

    /**
     * @param orders@Description: 方法说明 书籍兑换
     * @Author: zyy
     * @date: 2018/12/20 15:30
     */
    @Override
    public int addBookOrder(Orders orders,int ubalance) {
        Map<String,Object> param=new HashMap<>();
        param.put("ubalance",ubalance);
        param.put("uid",orders.getUid());
        usersMapper.updateUserBalance(param);
        int count= this.insertSelective(orders);
        return count;
    }

    /**
     * @param orders@Description: 方法说明 检查用户是否已经购买书本
     * @Author: zyy
     * @date: 2018/12/20 16:21
     */
    @Override
    public Orders checkUserBuyBook(Orders orders) {
        return ordersMapper.checkUserBuyBook(orders);
    }
}
