package com.ykyd.eb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entity基类
 * @author zouyi
 * 基于代码复用和模型分离的思想，在项目开发中使用JPA的@MappedSuperclass注解将实体类的多个属性分别封装到不同的非实体类中。
 * 例如，数据库表中都需要id来表示编号，id是这些映射实体类的通用的属性，
 * 交给jpa统一生成主键id编号，那么使用一个父类来封装这些通用属性，
 * 并用@MappedSuperclas标识。
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	/***serialVersionUID */
	private static final long serialVersionUID = -3739968330098043567L;
	
	/** ID */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /** 创建日期 */
	@Column(name="create_date")
    private Date createDate;

    /** 最近修改日期 */
	@Column(name="last_modify_date")
    private Date lastModifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
}
