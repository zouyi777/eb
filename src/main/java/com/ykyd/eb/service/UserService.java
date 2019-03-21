package com.ykyd.eb.service;

import com.ykyd.eb.Principal;
import com.ykyd.eb.entity.UserEntity;

/**
 * UserService 接口
 * @author zouyi
 *
 */
public interface UserService extends BaseService<UserEntity, Long> {
	/**
     * 根据用户名查找用户（忽略大小写）
     * @param username 用户名
     * @return 会员，若不存在则返回null
     */
	UserEntity findByUsername(String username);
	/**
	 * 判断用户是否已登录
	 *@return
	 */
	boolean authorized();
	/**
	 * 获取当前登录用户的Id
	 *@return
	 */
	Long getCurrentId();
	/**
     * 获取当前身份信息
     * 
     * @return 身份信息，不存在时返回NULL
     */
	Principal getCurrentPrincipal();
}
