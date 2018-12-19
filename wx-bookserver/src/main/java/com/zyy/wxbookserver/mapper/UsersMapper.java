package com.zyy.wxbookserver.mapper;

import com.zyy.wxbookserver.basemapper.IBaseMapper;
import com.zyy.wxbookserver.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UsersMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUid(String uid);
}