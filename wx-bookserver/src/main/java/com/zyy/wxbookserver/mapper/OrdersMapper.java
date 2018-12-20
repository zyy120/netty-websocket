package com.zyy.wxbookserver.mapper;

import com.zyy.wxbookserver.basemapper.IBaseMapper;
import com.zyy.wxbookserver.model.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface OrdersMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int getOrderyBuyCount(Map<String,Object> param);

    /**
     * @param
     * @Description: 方法说明 检查用户是否已经购买书本
     * @Author: zyy
     * @date: 2018/12/20 16:21
     */
    Orders checkUserBuyBook (Orders orders);
}