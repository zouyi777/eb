package com.ykyd.eb.dao;

import com.ykyd.eb.entity.UserEntity;

/**
 * UserDao接口
 * @author zouyi
 *
 */
public interface UserDao extends BaseDao<UserEntity, Long> {
	/**
     * 根据用户名查找用户（忽略大小写）
     * @param username 用户名
     * @return 会员，若不存在则返回null
     */
	UserEntity findByUsername(String username);
}
