package com.spring.zyy.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.spring.zyy.pojo.User;

@Repository
public interface IUserDao {
	
	public User findByAll (@Param(value="id")int id);

}
