package com.ykyd.eb.dao;

import com.ykyd.eb.entity.User;

public interface UserDao {
	public User find(String userName,String password);
}
