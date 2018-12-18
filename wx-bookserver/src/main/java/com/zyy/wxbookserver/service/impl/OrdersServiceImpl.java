package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.OrdersMapper;
import com.zyy.wxbookserver.model.Orders;
import com.zyy.wxbookserver.service.IOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Resource
    private OrdersMapper ordersMapper;
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
}
