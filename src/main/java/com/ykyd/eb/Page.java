package com.ykyd.eb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * @author zouyi
 *
 * @param <T>
 */
public class Page<T> implements Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = -3239292827116975324L;
	/** 分页信息 */
    private Pageable pageable;
    /** 数据集合*/
    private List<T> dataList;
    /** 总记录数 */
    private Long totalCounts;
    /** 总页数 */
    private Integer totalPages;
    
    public Page() {
        this.pageable = new Pageable();
        this.dataList = new ArrayList<T>();
        this.totalCounts = 0L;
        this.totalPages = 0;
    }
    /**
     * @param pageable
     *            分页信息
     * @param cont
     *            内容
     * @param total
     *            总记录数
     */
    public Page(Pageable pageable, List<T> dataList, Long totalCounts) {
        this.pageable = pageable;
        this.dataList = dataList;
        this.totalCounts = totalCounts;
        this.totalPages = (int) Math.ceil((double) totalCounts / (double) getPageSize());
        if (getTotalPages() < getPageable().getPageNumber()) {
            getPageable().setPageNumber(getTotalPages());
        }
    }

    public Pageable getPageable() {
        return pageable;
    }

    public Integer getPageNumber() {
        return getPageable().getPageNumber();
    }

    public Integer getPageSize() {
        return getPageable().getPageSize();
    }
    
    public List<T> getDataList() {
        return dataList;
    }

    public Long getTotalCounts() {
        return totalCounts;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
