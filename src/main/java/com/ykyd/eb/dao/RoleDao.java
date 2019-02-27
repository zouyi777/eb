package com.ykyd.eb.dao;

import java.util.List;

import com.ykyd.eb.entity.RoleEntity;

/**
 * 角色Dao
 * @author zouyi
 */
public interface RoleDao extends BaseDao<RoleEntity,Long>{
	
	/**
	 * 通过userId查询角色列表
	 *@param userId
	 *@return
	 */
	List<RoleEntity> findRoleListByUserId(Long userId);

}
