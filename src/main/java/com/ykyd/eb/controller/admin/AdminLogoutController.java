package com.ykyd.eb.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminLogoutController")
@RequestMapping("/adminlogout")
public class AdminLogoutController {
	/**
     * 注销
     */
    @RequestMapping
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    	Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/adminlogin";
    }
}
