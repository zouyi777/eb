package com.ykyd.eb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.entity.UserInfoEntity;
import com.ykyd.eb.service.FileService;
import com.ykyd.eb.service.UserInfoService;
import com.ykyd.eb.service.UserService;
import com.ykyd.eb.vo.UserInfoVo;

/**
 * 前台用户账户中心
 * @author zouyi
 */
@Controller("accountController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/account") //截获带有/login的请求
public class AccountController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
    private FileService fileService;
	
	@RequestMapping(method=RequestMethod.GET)
    public String get(){
        return "account";
    }
	/**
	 * 获取账户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/account",method=RequestMethod.GET)
	@ResponseBody
	public UserInfoVo getAccount(){
		Long currentId = userService.getCurrentId();
		UserInfoEntity userInfo = userInfoService.findById(currentId);
		if(userInfo!=null){
			Long userId= userInfo.getUserId();
			UserEntity  userEntity = userService.findById(userId);
			if(userEntity!=null){
				UserInfoVo userInfoVo = new UserInfoVo();
				userInfoVo.setUser(userEntity);
				userInfoVo.setUserInfo(userInfo);
				return userInfoVo;
			}
		}
		return null;
	}
}
