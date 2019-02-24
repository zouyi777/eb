package com.ykyd.eb.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;

/** 
 * 前台用户登陆
 */
@Controller("loginController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/login") //截获带有/login的请求
public class LoginController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "login";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String post(String username,String password){  //用来处理用户的登陆请求
    	log.info("用户名："+username+"   密码："+username);
    	UserEntity userEntity=userService.findByUsername(username);
        if(userEntity!=null && userEntity.getPassword()!=null
        		&& userEntity.getPassword().equals(password)){
        	return "redirect:/index";
        }
        return "login";
    }
}