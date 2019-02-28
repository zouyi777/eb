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

import com.ykyd.eb.entity.AdminEntity;
import com.ykyd.eb.entity.RoleEntity;
import com.ykyd.eb.service.AdminService;
import com.ykyd.eb.service.RoleService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Resource
	private AdminService adminService;
	@Resource
	private RoleService roleService;
	/**
	 * 获取账号授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Principal principal = (Principal)principals.getPrimaryPrincipal();
		if(principal!=null){
			AdminEntity adminEntity=adminService.findByUsername(principal.getUsername());
			if(adminEntity!=null){
				List<RoleEntity> roleList= roleService.findRoleListByUserId(adminEntity.getId());
				for(RoleEntity roleEntity:roleList){
					authorizationInfo.addRole(roleEntity.getRoleName());
				}
			}
		}
		Long id=adminService.getCurrentId();
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
        AdminEntity adminEntity=adminService.findByUsername(userName);
        if(adminEntity!=null && adminEntity.getPassword()!=null
        		&& adminEntity.getPassword().equals(password)){
        	
        	return new SimpleAuthenticationInfo(new Principal(adminEntity.getId(), userName),password,getName());
        }
        throw new UnauthenticatedException();
	}

}
