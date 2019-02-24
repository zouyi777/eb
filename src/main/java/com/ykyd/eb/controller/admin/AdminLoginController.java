package com.ykyd.eb.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * 后台管理登录
 */
@Controller("adminLoginController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/adminlogin")//截获带有/admin/login的请求
public class AdminLoginController {
	
	private static Logger log = Logger.getLogger(AdminLoginController.class);
	
    @RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "admin/login";
    }
}