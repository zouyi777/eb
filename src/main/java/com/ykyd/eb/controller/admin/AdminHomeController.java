package com.ykyd.eb.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminHomeController")
@RequestMapping("/admin/home")
public class AdminHomeController {
	private static Logger log = Logger.getLogger(AdminHomeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(){  
        return "admin/home";
    }
}
