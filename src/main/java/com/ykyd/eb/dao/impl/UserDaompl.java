package com.ykyd.eb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ykyd.eb.dao.UserDao;
import com.ykyd.eb.entity.User;

@Repository
public class UserDaompl implements UserDao {

	@Resource
    private SessionFactory sessionFactory;
	
	@Override
	public User find(String userName, String password) {
		String hql = "from User where userName = :username and password = :password";
		@SuppressWarnings("unchecked")
		List<User> users = sessionFactory.openSession().createQuery(hql).setString("username", userName).setString("password", password).list();
        return users.size()>0?(User)users.get(0):null;
	}

}