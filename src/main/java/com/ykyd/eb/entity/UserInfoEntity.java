package com.ykyd.eb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户信息
 * @author zouyi
 *
 */
@Entity
@Table(name="tb_userinfo")
public class UserInfoEntity extends BaseEntity {
	
	/****/
	private static final long serialVersionUID = -233715423200210243L;

	/**用户昵称*/
	@Column(name="nick_name", length=50)
	private String nickName;
	
	/**用户真实姓名*/
	@Column(name="real_name")
	private String realName;
	
	/**身份证号码*/
	@Column(name="identity_no")
	private String identityNo;
	
	/**电话号码*/
	@Column(name="phone_no")
	private String phoneNo;
	
	/**邮箱*/
	@Column(name="email")
	private String email;
	
	/**头像路径*/
	@Column(name="avatar_path")
	private String avatarPath;
	
	@Column(name="user_id")
	private Long userId;
	

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
