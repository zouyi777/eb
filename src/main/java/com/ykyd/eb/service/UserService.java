package com.ykyd.eb.service;

import com.ykyd.eb.entity.UserEntity;

/**
 * UserService 接口
 * @author zouyi
 *
 */
public interface UserService extends BaseService<UserEntity, Long> {
	/**
     * 根据用户名查找会员（忽略大小写）
     * @param username 用户名
     * @return 会员，若不存在则返回null
     */
	UserEntity findByUsername(String username);
}
