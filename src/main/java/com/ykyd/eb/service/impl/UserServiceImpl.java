package com.ykyd.eb.service.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ykyd.eb.Principal;
import com.ykyd.eb.dao.UserDao;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;
/**
 * UserService 实现类
 * @author zouyi
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }
	@Override
	public UserEntity findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	@Override
	public boolean authorized() {
		Principal currentPrincipal = getCurrentPrincipal();
        if (currentPrincipal != null) {
            return true;
        }
		return false;
	}
	@Override
	public Long getCurrentId() {
		Principal currentPrincipal = getCurrentPrincipal();
        if (currentPrincipal != null) {
            return currentPrincipal.getId();
        }
		return null;
	}
	@Override
	public Principal getCurrentPrincipal() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return (Principal) request.getSession().getAttribute(UserEntity.PRINCIPAL_ATTR_NAME);
        }
		return null;
	}
}
