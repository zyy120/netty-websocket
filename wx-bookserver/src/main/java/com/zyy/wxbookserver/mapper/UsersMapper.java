package com.zyy.wxbookserver.mapper;

import com.zyy.wxbookserver.basemapper.IBaseMapper;
import com.zyy.wxbookserver.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUid(String uid);

    Users selectBySkey(String skey);

    int updateUserBalance(Map<String,Object> param);

    /**
     * @param
     * @Description: 方法说明 获取用户购买的书籍
     * @Author: zyy
     * @date: 2018/12/20 16:10
     */
    List<Map<String, Object>> getUserBuyBooks(String skey);
}