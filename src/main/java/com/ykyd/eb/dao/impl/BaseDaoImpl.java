package com.ykyd.eb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;
import com.ykyd.eb.dao.BaseDao;

/**
 * Dao 基础接口实现类
 * @author zouyi
 * @param <T>
 * @param <ID>
 */
public class BaseDaoImpl <T, ID extends Serializable> implements BaseDao<T, ID> {
	/** 实体类类文件 */
	protected Class<T> entityClass;
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
        // 获取超类对象类型
        Type type = getClass().getGenericSuperclass();
        // 获取实际类型参数
        Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
        // 获取实体类模版
        entityClass = (Class<T>) parameterizedType[0];
    }

	@Override
	public void save(T t) {
		entityManager.persist(t);
	}

	@Override
	public void delete(T t) {
		entityManager.remove(t);
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		return entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> finAll() {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		return null;
	}

	@Override
	public Page<T> findPage(Pageable pageable) {
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
//		if (pageable == null) {
//            pageable = new Pageable();
//        }
//		criteria.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//		criteria.setMaxResults(pageable.getPageSize());
//		List<T> results =criteria.list();
		return null;
	}
}
