package com.howie.service.impl;

import com.howie.constant.CacheConstan;
import com.howie.mapper.TUserMapper;
import com.howie.model.TUser;
import com.howie.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    
    @Resource
    private TUserMapper tUserMapper;
    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return tUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(TUser record) {
        return tUserMapper.insert(record);
    }

    @Override
    public int insertSelective(TUser record) {
        return tUserMapper.insertSelective(record);
    }

    @Override
    @Cacheable(value ={ CacheConstan.RedisConstan.USERBYID }, key = "#root.methodName+':'+#root.args[0]")
    public TUser selectByPrimaryKey(Integer userId) {
        return tUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(TUser record) {
        return tUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TUser record) {
        return tUserMapper.updateByPrimaryKey(record);
    }
}
