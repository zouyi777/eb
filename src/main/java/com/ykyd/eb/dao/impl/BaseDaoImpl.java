package com.ykyd.eb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

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
	/** 别名前缀 */
    protected static final String aliasPrefix = "ibsGeneratedAlias";
    /** 别名数 */
    protected static volatile long aliasCount = 0;
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
	public void persist(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be empty");
        }
        entityManager.persist(entity);
	}

	@Override
	public void remove(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be empty");
        }
        entityManager.remove(entity);
	}

	@Override
	public T merge(T entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Entity must not be empty");
        }
        return entityManager.merge(entity);
	}

	@Override
	public T findById(ID id) {
		if (id == null) {
            return null;
        }
        return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		// 获取条件构造器
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 创建条件查询
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        // 设置查询ROOT
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        // 执行查询
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        return query.getResultList();
	}

	@Override
	public Page<T> findPage(Pageable pageable) {
		// 获取条件构造器
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 创建条件查询
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        // 设置查询ROOT
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
     // 执行查询
        Long total = count(criteriaBuilder, criteriaQuery, root);
        int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
        if (totalPages < pageable.getPageNumber()) {
            pageable.setPageNumber(totalPages);
        }
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<T> results = query.getResultList();
        return new Page<T>(pageable, results, total);
	}

	@Override
	public Long count() {
		// 获取条件构造器
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // 创建条件查询
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        // 设置查询ROOT
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        // 查询实体数量
        return count(criteriaBuilder, criteriaQuery, root);
	}
	
	/**
     * 查询实体数量
     * 
     * @param criteriaBuilder
     *            条件构造器
     * @param criteriaQuery
     *            条件查询
     * @param root
     *            查询ROOT
     * @return 实体数量
     */
    private Long count(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root) {
        // 创建计算条件查询
        CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        for (Iterator<Root<?>> iterator = criteriaQuery.getRoots().iterator(); iterator.hasNext();) {
            Root<?> countRoot = iterator.next();
            Root<?> destCountRoot = countCriteriaQuery.from(countRoot.getJavaType());
            destCountRoot.alias(getAlias(countRoot));
            // 复制联表
            copyJoins(countRoot, destCountRoot);
        }
        // 设置计算查询ROOT
        countCriteriaQuery.select(criteriaBuilder.count(root));
        // 设置默认GROUP分组
        if (criteriaQuery.getGroupList() != null) {
            countCriteriaQuery.groupBy(criteriaQuery.getGroupList());
        }
        // 设置默认GROUP限制条件
        if (criteriaQuery.getGroupRestriction() != null) {
            countCriteriaQuery.having(criteriaQuery.getGroupRestriction());
        }
        // 设置默认WHERE限制条件
        if (criteriaQuery.getRestriction() != null) {
            countCriteriaQuery.where(criteriaQuery.getRestriction());
        }
        // 执行查询
        return entityManager.createQuery(countCriteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }
    
    /**
     * 获取别名
     * 
     * @param selection
     *            选择
     * @return 别名
     */
    private synchronized String getAlias(Selection<?> selection) {
        if (selection == null) {
            return null;
        }
        String alias = selection.getAlias();
        if (alias == null) {
            if (aliasCount >= 1000) {
                aliasCount = 0;
            }
            alias = aliasPrefix + aliasCount++;
            selection.alias(alias);
        }
        return alias;
    }
    
    /**
     * 复制联表
     * 
     * @param from
     *            主表
     * @param to
     *            子表
     */
    private void copyJoins(From<?, ?> from, From<?, ?> to) {
        for (Iterator<?> iterator = from.getJoins().iterator(); iterator.hasNext();) {
            Join<?, ?> join = (Join<?, ?>) iterator.next();
            Join<?, ?> toJoin = to.join(join.getAttribute().getName(), join.getJoinType());
            // 获取并设置别名
            toJoin.alias(getAlias(join));
            copyJoins(join, toJoin);
        }
        for (Iterator<?> iterator = from.getFetches().iterator(); iterator.hasNext();) {
            Fetch<?, ?> fetch = (Fetch<?, ?>) iterator.next();
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            // 复制抓取
            copyFetches(fetch, toFetch);
        }
    }
    
    /**
     * 复制抓取（fetch=FetchType.LAZY）
     * 
     * @param from
     *            主表
     * @param to
     *            子表
     */
    private void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {
        for (Iterator<?> iterator = from.getFetches().iterator(); iterator.hasNext();) {
            Fetch<?, ?> fetch = (Fetch<?, ?>) iterator.next();
            Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
            copyFetches(fetch, toFetch);
        }
    }


}
