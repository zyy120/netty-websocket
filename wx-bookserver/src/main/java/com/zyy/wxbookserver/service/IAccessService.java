package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Access;

public interface IAccessService {

    int deleteByPrimaryKey(Integer id);

    int insert(Access record);

    int insertSelective(Access record);

    Access selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKey(Access record);
}
