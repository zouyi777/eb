package com.ykyd.eb.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.ykyd.eb.dao.RoleDao;
import com.ykyd.eb.entity.RoleEntity;

/**
 * 角色Dao实现类
 * @author zouyi
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<RoleEntity, Long> implements RoleDao {

	@Override
	public List<RoleEntity> findRoleListByUserId(Long userId) {
		if (null==userId) {
            return null;
        }
        try {
            String jpql = "select roles from RoleEntity roles where roles.userId = :userId";
            return entityManager.createQuery(jpql, RoleEntity.class).setFlushMode(FlushModeType.COMMIT)
                    .setParameter("userId", userId).getResultList();
        } catch (NoResultException e) {
            return null;
        }
	}

}
