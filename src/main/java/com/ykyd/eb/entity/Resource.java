package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**资源*/
@Entity
@Table(name="tb_resource")
public class Resource {

	/**资源Id*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	/**资源名*/
	@Column(name="res_name",length=50,nullable=false)
	private String resName;
	
	@Column(name="role_id")
	private long roleId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
