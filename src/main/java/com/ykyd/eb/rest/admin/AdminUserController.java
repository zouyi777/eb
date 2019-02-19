package com.ykyd.eb.rest.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminUserController")
@RequestMapping("/admin/user")
public class AdminUserController {
	private static Logger log = Logger.getLogger(AdminUserController.class);
	
	@RequestMapping(value="/list_user",method=RequestMethod.GET)
    public String listUser(){  
        return "admin/user/list_user";
    }
	
	@RequestMapping(value = "/add_user",method=RequestMethod.GET)
	public String addUser(){
		return "admin/user/add_user";
	}
}
