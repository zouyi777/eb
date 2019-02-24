package com.ykyd.eb.controller.admin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;

@Controller("adminUserController")
@RequestMapping("/admin/user")
public class AdminUserController {
	private static Logger log = Logger.getLogger(AdminUserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/list_user",method=RequestMethod.GET)
    public String listUser(){  
        return "admin/user/list_user";
    }
	
	@RequestMapping(value = "/add_user",method=RequestMethod.GET)
	public String addUser(){
		return "admin/user/add_user";
	}
}
