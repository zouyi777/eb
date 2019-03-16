package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 管理员实体
 * @author zouyi
 */
@Entity
@Table(name="tb_admin")
public class AdminEntity extends BaseEntity{
	
	/*** */
	private static final long serialVersionUID = 8223534749483657464L;

	/**管理员登录名*/
	@Column(name="admin_name", length=50, nullable=false)
    private String adminName;
	
	/**管理员登录密码*/
	@Column(name="admin_password", length=50, nullable=false)
    private String adminPassword;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}
