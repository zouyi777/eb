package com.ykyd.eb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ykyd.eb.entity.UserEntity;

/**
 * 前台用户注销
 * @author zouyi
 */
@Controller("logoutController")
@RequestMapping("/logout")
public class LogoutController {
	/**
     * 注销
     */
    @RequestMapping
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //移除保存在Session中的用户信息
    	session.removeAttribute(UserEntity.PRINCIPAL_ATTR_NAME);
    	return "redirect:/login";
    }
}
