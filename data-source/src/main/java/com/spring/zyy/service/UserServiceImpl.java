package com.spring.zyy.service;

import javax.annotation.Resource;

import com.spring.zyy.datasources.DataSource;
import com.spring.zyy.datasources.DataSourceInstances;
import org.springframework.stereotype.Service;

import com.spring.zyy.dao.IUserDao;
import com.spring.zyy.pojo.User;
import com.spring.zyy.service.impl.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	@DataSource(value = DataSourceInstances.IN)
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.findByAll(userId);
	}

	

}