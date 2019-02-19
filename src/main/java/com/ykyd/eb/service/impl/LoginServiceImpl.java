package com.ykyd.eb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykyd.eb.dao.UserDao;
import com.ykyd.eb.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public int login(String userName, String password) {
		return userDao.find(userName, password)==null ? 0:1;
	}

}
