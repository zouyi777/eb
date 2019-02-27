package com.ykyd.eb.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("admHomeController")
@RequestMapping("/admin/home")
public class AdmHomeController {
	private static Logger log = Logger.getLogger(AdmHomeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(){  
        return "admin/home";
    }
}
