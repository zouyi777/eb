package com.ykyd.eb.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;
import com.ykyd.eb.dao.BaseDao;
import com.ykyd.eb.service.BaseService;
/**
 * Service 基础接口实现类
 * @author zouyi
 *
 * @param <T>
 * @param <ID>
 */
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	
	/** 基类DAO */
    private BaseDao<T, ID> baseDao;

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }
    
	@Override
	@Transactional
	public T save(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        baseDao.persist(entity);
        return entity;
	}

	@Override
	@Transactional
	public void delete(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be empty");
        }
        baseDao.remove(entity);
		
	}

	@Override
	@Transactional
	public void delete(ID id) {
		if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        delete(findById(id));
	}

	@Override
	@Transactional
	public T update(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be empty");
        }
        return baseDao.merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(ID id) {
		if (id == null) {
            return null;
        }
        return baseDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> findPage(Pageable pageable) {
		if (pageable == null) {
            pageable = new Pageable();
        }
        return baseDao.findPage(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return baseDao.count();
	}

}
