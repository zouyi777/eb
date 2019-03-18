package com.ykyd.eb.vo;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.entity.UserInfoEntity;

/**
 * 用户信息VO
 * @author zouyi
 *
 */
public class UserInfoVo {
	
	/** 用户*/
	private UserEntity user;
	/***用户信息*/
	private UserInfoEntity userInfo;
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public UserInfoEntity getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
	}
}
