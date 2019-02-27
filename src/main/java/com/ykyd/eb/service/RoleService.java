package com.ykyd.eb.service;

import java.util.List;

import com.ykyd.eb.entity.RoleEntity;

/**
 * 角色Service接口
 * @author zouyi
 */
public interface RoleService extends BaseService<RoleEntity, Long> {
	/**
	 * 通过userId查询角色列表
	 *@param userId
	 *@return
	 */
	List<RoleEntity> findRoleListByUserId(Long userId);
}
