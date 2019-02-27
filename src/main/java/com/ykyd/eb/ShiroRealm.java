package com.ykyd.eb;


import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ykyd.eb.entity.RoleEntity;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.RoleService;
import com.ykyd.eb.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	/**
	 * 获取账号授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = (String)principals.getPrimaryPrincipal();
		UserEntity userEntity=userService.findByUsername(username);
		if(userEntity!=null){
			List<RoleEntity> roleList= roleService.findRoleListByUserId(userEntity.getId());
			for(RoleEntity roleEntity:roleList){
				authorizationInfo.addRole(roleEntity.getRoleName());
			}
		}
		return authorizationInfo;
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
        UserEntity userEntity=userService.findByUsername(userName);
        if(userEntity!=null && userEntity.getPassword()!=null
        		&& userEntity.getPassword().equals(password)){
        	
        	return new SimpleAuthenticationInfo(userName,password,getName());
        }
        throw new UnauthenticatedException();
	}

}
