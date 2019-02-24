package com.ykyd.eb.test;

import javax.annotation.Resource;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;
import com.ykyd.eb.service.impl.UserServiceImpl;

/**
 * 测试用户服务
 * 因为这些方法都是写在基类里面的，所以测试了UserService，就能把这些基类中的方法都测试了
 * @author zouyi
 *
 */
public class UserServiceTest {
	@Resource
	private UserService userService = new UserServiceImpl();
	
	/**
	 * 测试保存用户
	 */
	public void testSaveUser(){
		UserEntity user = new UserEntity();
		user.setUserName("zy1");
		user.setPassword("123456");
		userService.save(user);
	}
}
