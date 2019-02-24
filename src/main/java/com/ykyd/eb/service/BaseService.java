package com.ykyd.eb.service;

import java.io.Serializable;
import java.util.List;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;

/**
 * service 基础接口
 * @author zouyi
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {
	/**
     * 保存实体
     * @param entity 实体
     */
    T save(T entity);
    /**
     * 删除实体
     * @param entity 实体
     */
    void delete(T entity);
    /**
     * 删除实体
     * 
     * @param id
     *            ID
     */
    void delete(ID id);
    /**
     * 更新实体
     * @param entity实体
     * @return 实体
     */
    T update(T entity);
    
    /**
     * 查找实体
     * @param id ID
     * @return 实体
     */
    T findById(ID id);
    /**
	 * 查找所有实体
	 * @return 实体集合
	 */
	List<T> findAll();
	
	/**
     * 查找实体分页
     * @param pageable 分页信息
     * @return 实体分页
     */
    Page<T> findPage(Pageable pageable);
    /**
     * 查询实体数量
     * @return 实体数量
     */
    Long count();
    
}
