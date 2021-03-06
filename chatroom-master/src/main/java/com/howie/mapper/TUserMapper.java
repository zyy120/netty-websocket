package com.howie.mapper;

import com.howie.basemapper.IBaseMapper;
import com.howie.model.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}