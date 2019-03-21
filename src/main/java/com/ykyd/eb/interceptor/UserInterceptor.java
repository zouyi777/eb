package com.ykyd.eb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ykyd.eb.Principal;
import com.ykyd.eb.entity.UserEntity;


/**
 * 用户请求拦截器
 * @author zouyi
 */
public class UserInterceptor extends HandlerInterceptorAdapter{
	
	/** 默认登录URL */
    private static final String DEFAULT_LOGIN_URL = "/login";
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		// 判断是否当前身份已登录
        Principal principal = (Principal) session.getAttribute(UserEntity.PRINCIPAL_ATTR_NAME);
        if (principal != null) {
            return true;
        }else{
        	response.sendRedirect(request.getContextPath() + DEFAULT_LOGIN_URL);
        	return false;
        }
	}
}
