package com.ykyd.eb;

import java.io.Serializable;

/**
 * 分页信息
 * @author zouyi
 *
 */
public class Pageable implements Serializable {

	/**serialVersionUID*/
	private static final long serialVersionUID = 3830117588716158768L;
	/** 默认页码 */
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    /** 默认每页记录数 */
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    /** 最大每页记录数 */
    private static final Integer MAX_PAGE_SIZE = 1000;

    /** 页码 */
    private Integer pageNumber = DEFAULT_PAGE_NUMBER;

    /** 每页记录数 */
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    
    public Pageable() {
    	
    }
    
    /**
     * @param pageNumber 页码
     * @param pageSize 每页记录数
     */
    public Pageable(Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageNumber > 0) {
            this.pageNumber = pageNumber;
        }
        if (pageSize != null && pageSize > 0 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }
    
    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        this.pageNumber = pageNumber;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }
}
