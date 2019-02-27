package com.ykyd.eb.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ykyd.eb.dao.RoleDao;
import com.ykyd.eb.entity.RoleEntity;
import com.ykyd.eb.service.RoleService;

/**
 * 角色Service实现类
 * @author zouyi
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Long> implements RoleService {
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
    public void setBaseDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
    }
	@Override
	public List<RoleEntity> findRoleListByUserId(Long userId) {
		return roleDao.findRoleListByUserId(userId);
	}
	
}
