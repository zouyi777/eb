package com.ykyd.eb.rest.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ykyd.eb.entity.User;
import com.ykyd.eb.service.LoginService;

/** 
 * 后台管理登录
 */
@Controller("adminLoginController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/admin/login")//截获带有/admin/login的请求
public class AdminLoginController {
	
	private static Logger log = Logger.getLogger(AdminLoginController.class);
	
	@Autowired
	private LoginService loginService;

    @RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "admin/admin_login";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String post(String username,String password){  //用来处理用户的登陆请求
    	log.info("用户名："+username+"   密码："+password);
        if(loginService.login(username, password) == 1){
        	return "redirect:/admin/home";
        }
        return "admin/admin_login";
    }
}
