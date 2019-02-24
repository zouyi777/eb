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

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	/**
	 * 获取账号授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("admin");
//		for(int i=3;i<10;i++){
//			UserEntity user = new UserEntity();
//			user.setUserName("zy"+i);
//			user.setPassword("123456");
//			userService.save(user);
//		}
		UserEntity user = userService.findById(Long.valueOf(10));
//		user.setPassword("123456789");
//		userService.update(user);
		userService.delete(userService.update(user));
//		List<UserEntity> userList=userService.findAll();
//		Pageable pageable = new Pageable();
//		pageable.setPageNumber(2);
//		pageable.setPageSize(3);
//		
//		Page<UserEntity> page=userService.findPage(pageable);
//		List<UserEntity> userList = page.getDataList();
//		for(UserEntity user : userList){
//			System.out.println("id:"+user.getId()+" name:"+user.getUserName()+" password:"+user.getPassword());
//		}
//		System.out.print("总记录数："+page.getTotalCounts()+"总页数："+page.getTotalPages());
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
