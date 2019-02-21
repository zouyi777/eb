package com.ykyd.eb.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ykyd.eb.service.LoginService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private LoginService loginService;
	/**
	 * 获取账号授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
	/**
	 * 获取身份认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// 获取登录令牌
		 UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        //获取登录信息
        String  userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if(loginService.login(userName, password) == 1){
        	return new SimpleAuthenticationInfo(userName,password,getName());
        }
        throw new UnauthenticatedException();
	}

}
