package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Users;

import java.util.List;
import java.util.Map;

public interface IUsersService {

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUid(String uid);

    Users selectBySkey(String skey);

    /**
     * @param
     * @Description: 方法说明 获取用户购买的书籍
     * @Author: zyy
     * @date: 2018/12/20 16:10
     */
    List<Map<String, Object>> getUserBuyBooks(String skey);
}
