package com.ykyd.eb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("unauthorizedController")
@RequestMapping("/unauthorized")
public class UnauthorizedController {
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "unauthorized";
    }
}
