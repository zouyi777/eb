package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**用户*/
@Entity
@Table(name="tb_user")
public class UserEntity extends BaseEntity {
	
	/***/
	private static final long serialVersionUID = -4768331502322895110L;
	
	/**用户名*/
	@Column(name="user_name", length=50, nullable=false)
    private String userName;
	
	/**用户密码*/
	@Column(name="user_password", length=50, nullable=false)
    private String password;


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
