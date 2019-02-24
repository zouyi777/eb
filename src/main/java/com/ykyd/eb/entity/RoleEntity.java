package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**角色*/
@Entity
@Table(name="tb_role")
public class RoleEntity {
	
	/**角色id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**角色名*/
	@Column(name="role_name", length=50, nullable=false)
	private String roleName;
	
	@Column(name="user_id")
	private Long userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
