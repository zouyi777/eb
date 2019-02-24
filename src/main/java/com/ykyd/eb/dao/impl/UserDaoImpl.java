package com.ykyd.eb.dao.impl;


import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import com.ykyd.eb.dao.UserDao;
import com.ykyd.eb.entity.UserEntity;
/**
 * UserDao实现类
 * @author zouyi
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity, Long> implements UserDao{

	@Override
	public UserEntity findByUsername(String username) {
		if (null==username || "".equals(username)) {
            return null;
        }
        try {
            String jpql = "select users from UserEntity users where lower(users.userName) = lower(:username)";
            return entityManager.createQuery(jpql, UserEntity.class).setFlushMode(FlushModeType.COMMIT)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

}
