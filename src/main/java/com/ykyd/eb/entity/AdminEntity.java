package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员实体
 * @author zouyi
 */
@Entity
@Table(name="tb_admin")
public class AdminEntity {
	/**用户id*/
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**用户名*/
	@Column(name="user_name", length=50, nullable=false)
    private String userName;
	
	/**用户密码*/
	@Column(name="user_password", length=50, nullable=false)
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
