package com.ykyd.eb.service;

import com.ykyd.eb.entity.AdminEntity;

/**
 * 管理员Service接口
 * @author zouyi
 */
public interface AdminService extends BaseService<AdminEntity, Long> {
	/**
     * 根据用户名查找管理员（忽略大小写）
     * @param username 用户名
     * @return 会员，若不存在则返回null
     */
	AdminEntity findByAdminName(String adminName);
	/**
	 * 获取当前登录管理员的Id
	 *@return
	 */
	Long getCurrentId();
}
