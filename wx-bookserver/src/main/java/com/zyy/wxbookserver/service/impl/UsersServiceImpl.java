package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.UsersMapper;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.service.IUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl  implements IUsersService {
    @Resource
    private UsersMapper usersMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Override
    public Users selectByUid(String uid) {
        return usersMapper.selectByUid(uid);
    }

    @Override
    public Users selectBySkey(String skey) {
        return usersMapper.selectBySkey(skey);
    }

    /**
     * @param skey@Description: 方法说明 获取用户购买的书籍
     * @Author: zyy
     * @date: 2018/12/20 16:10
     */
    @Override
    public List<Map<String, Object>> getUserBuyBooks(String skey) {
        return usersMapper.getUserBuyBooks(skey);
    }
}
