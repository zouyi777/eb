package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**用户*/
@Entity
@Table(name="tb_user")
public class User {
	
	/**用户id*/
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/**用户名*/
	@Column(name="user_name", length=50, nullable=false)
    private String userName;
	
	/**用户密码*/
	@Column(name="user_password", length=50, nullable=false)
    private String password;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password="
                + password + "]";
    }
}
