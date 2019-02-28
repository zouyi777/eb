package com.ykyd.eb.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.ykyd.eb.dao.AdminDao;
import com.ykyd.eb.entity.AdminEntity;

/**
 * 管理员Dao实现类
 * @author zouyi
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<AdminEntity, Long> implements AdminDao {

	@Override
	public AdminEntity findByUsername(String username) {
		if (null==username || "".equals(username)) {
            return null;
        }
        try {
            String jpql = "select admin from AdminEntity admin where lower(admin.userName) = lower(:username)";
            return entityManager.createQuery(jpql, AdminEntity.class).setFlushMode(FlushModeType.COMMIT)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

}
