package com.howie.mapper;

import com.howie.model.TUser;
import com.qs.common.base.basemapper.IBaseMapper;

public interface TUserMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}