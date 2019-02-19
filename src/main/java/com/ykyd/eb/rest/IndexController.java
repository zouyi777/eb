package com.ykyd.eb.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("indexController")
@RequestMapping("/index")
public class IndexController {
	private static Logger log = Logger.getLogger(IndexController.class);
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(){  
        return "index";
    }
}
