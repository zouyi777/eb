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
public class Role {
	
	/**角色id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**角色名*/
	@Column(name="role_name", length=50, nullable=false)
	private String roleName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
