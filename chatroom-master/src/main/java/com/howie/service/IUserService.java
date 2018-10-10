package com.howie.service;

import com.howie.model.TUser;

public interface IUserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}
