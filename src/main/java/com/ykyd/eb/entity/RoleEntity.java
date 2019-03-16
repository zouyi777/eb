package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**角色*/
@Entity
@Table(name="tb_role")
public class RoleEntity extends BaseEntity {
	
	/***/
	private static final long serialVersionUID = -6723129107029987243L;

	/**角色名*/
	@Column(name="role_name", length=50, nullable=false)
	private String roleName;
	
	@Column(name="user_id")
	private Long userId;
	
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
