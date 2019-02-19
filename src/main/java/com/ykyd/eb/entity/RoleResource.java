package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**角色-资源*/
@Entity
@Table(name="tb_role_res")
public class RoleResource {

	/**角色-资源id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**角色id*/
	@Column(name="role_id",nullable=false)
	private Integer roleId;
	/**资源Id*/
	@Column(name="res_id",nullable=false)
	private Integer resId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
}
