package com.ykyd.eb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.Principal;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.RSAService;
import com.ykyd.eb.service.UserService;
import com.ykyd.eb.vo.LoginVo;

/** 
 * 前台用户登陆
 */
@Controller("loginController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/login") //截获带有/login的请求
public class LoginController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
    private RSAService rsaService;
	
    @RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "login";
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String post(LoginVo loginVo,HttpServletRequest request, HttpServletResponse response){  //用来处理用户的登陆请求
    	log.info("用户名："+loginVo.getUsername()+"   密码："+loginVo.getPassword());
    	UserEntity userEntity=userService.findByUsername(loginVo.getUsername());
    	//解密密码，并重新设置到loginVo
    	String decryptPassword = rsaService.decryptParameter("password", request);
    	loginVo.setPassword(decryptPassword);
        if(userEntity!=null && userEntity.getPassword()!=null && userEntity.getPassword().equals(loginVo.getPassword())){
        	HttpSession session = request.getSession();
        	// 判断会员是否已登录
        	if (userService.authorized()) {
                session.removeAttribute(UserEntity.PRINCIPAL_ATTR_NAME);
            }
        	//将用户信息保存在session中
        	session.setAttribute(UserEntity.PRINCIPAL_ATTR_NAME, new Principal(userEntity.getId(), userEntity.getUserName()));
        	return "success";
        }
        return "error";
    }
}
