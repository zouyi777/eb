package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**用户-角色*/
@Entity
@Table(name="tb_user_role")
public class UserRole {

	/**用户-角色id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**用户id*/
	@Column(name="user_id",nullable=false)
	private Integer userId;
	
	/**角色id*/
	@Column(name="role_id",nullable=false)
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
}
