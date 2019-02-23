package com.ykyd.eb.dao;

import java.io.Serializable;
import java.util.List;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;

/**
 *  Dao 基础接口
 * @author zouyi
 *
 * @param <T> 实体
 * @param <ID> 实体Id
 */ 
public interface BaseDao<T, ID extends Serializable> {
	
	/**
	 * 新增实体
	 *@param t
	 *@return
	 */
	void save(T t);
	/**
	 * 删除实体
	 *@param t
	 */
	void delete(T t);
	/**
	 * 更新实体
	 *@param t
	 */
	T update(T t);
	
	/**
	 * 通过id查找实体
	 * @param id
	 * @return 实体
	 */
	T findById(ID id);

	/**
	 * 查找所有实体
	 * @return
	 */
	List<T> finAll();
	/**
     * 查找实体分页
     * 
     * @param pageable
     *            分页信息
     * @return 实体分页
     */
    Page<T> findPage(Pageable pageable);
}
