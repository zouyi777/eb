package com.ykyd.eb.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;

@Controller("admUserController")
@RequestMapping("/admin/user")
public class AdmUserController {
	private static Logger log = Logger.getLogger(AdmUserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/list_user",method=RequestMethod.GET)
    public String listUser(){  
        return "admin/user/list_user";
    }
	
	@RequestMapping(value = "/add_user",method=RequestMethod.GET)
	public String addUser(HttpServletRequest request){
		return "admin/user/add_user";
	}
}
