package com.ykyd.eb.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;

@Controller("admUserController")
@RequestMapping("/admin/user")
public class AdmUserController {
	private static Logger log = Logger.getLogger(AdmUserController.class);
	 /** 每页记录数 */
    private static final int PAGE_SIZE = 10;
    
	@Resource
	private UserService userService;
	
	/**
	 * 获取用户列表视图
	 *@return
	 */
	@RequestMapping(value="/listuser",method=RequestMethod.GET)
    public String listUser(){  
        return "admin/user/listuser";
    }
	/**
	 * 获取新增用户视图
	 *@param request
	 *@return
	 */
	@RequestMapping(value = "/adduser",method=RequestMethod.GET)
	public String addUser(){
		return "admin/user/adduser";
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/adduser",method=RequestMethod.POST)
	public String addUser(UserEntity user){
		UserEntity  userEntity=userService.save(user);
		if(userEntity!=null){
			return "admin/user/listuser";
		}
		return "admin/user/adduser";
	}
	/**
	 * 获取用户列表数据
	 *@return
	 */
	@RequestMapping(value="/listuser_page",method=RequestMethod.GET)
	@ResponseBody
    public Page<UserEntity> listUserPage(Integer pageNumber){
		Pageable pageable = new Pageable();
		pageable.setPageNumber(pageNumber);
		pageable.setPageSize(PAGE_SIZE);
        return userService.findPage(pageable);
    }
	
}
