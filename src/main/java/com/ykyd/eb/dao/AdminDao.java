package com.ykyd.eb.dao;

import com.ykyd.eb.entity.AdminEntity;

/**
 * 管理员Dao接口
 * @author zouyi
 */
public interface AdminDao extends BaseDao<AdminEntity, Long> {
	/**
     * 根据用户名查找管理员（忽略大小写）
     * @param username 用户名
     * @return 会员，若不存在则返回null
     */
	AdminEntity findByUsername(String username);
}
