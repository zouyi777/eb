package com.ykyd.eb.controller.admin;


import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;
import com.ykyd.eb.bean.AvatarBean;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.entity.UserInfoEntity;
import com.ykyd.eb.enums.FileTypeEnum;
import com.ykyd.eb.service.FileService;
import com.ykyd.eb.service.UserInfoService;
import com.ykyd.eb.service.UserService;

@Controller("admUserController")
@RequestMapping("/admin/user")
public class AdmUserController {
	private static Logger log = Logger.getLogger(AdmUserController.class);
	 /** 每页记录数 */
    private static final int PAGE_SIZE = 10;
    
	@Resource
	private UserService userService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
    private FileService fileService;
	
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
		user.setCreateDate(new Date());
		user.setLastModifyDate(new Date());
		UserEntity  userEntity=userService.save(user);
		if(userEntity!=null){
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			userInfoEntity.setUserId(userEntity.getId());
			userInfoEntity.setCreateDate(new Date());
			userInfoEntity.setLastModifyDate(new Date());
			userInfoEntity.setAvatarPath("/upload/image/default_avatar.png");
			userInfoEntity.setNickName("昵称"+System.currentTimeMillis());
			userInfoEntity.setRealName("邹易");
			userInfoEntity.setIdentityNo(System.currentTimeMillis()+"");
			userInfoEntity.setPhoneNo(System.currentTimeMillis()+"");
			userInfoEntity.setEmail("185964885@qq.com");
			UserInfoEntity userInfoEntityResult = userInfoService.save(userInfoEntity);
			if(userInfoEntityResult!=null){
				return "admin/user/listuser";
			}
		}
		return "admin/user/adduser";
	}
	/**
	 * 获取用户列表数据
	 *@return
	 */
	@RequestMapping(value="/listuser_page",method=RequestMethod.GET)
	@ResponseBody
    public Page<UserInfoEntity> listUserPage(Integer pageNumber){
		Pageable pageable = new Pageable();
		pageable.setPageNumber(pageNumber);
		pageable.setPageSize(PAGE_SIZE);
        return userInfoService.findPage(pageable);
    }
	
	/**
	 * 删除单个用户
	 * @param Id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable Long id){
		userService.delete(id);
		return "success";
	}
	
	/**
	 * 获取用户详情视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String userDetail(){
		return "admin/user/detail";
	}
	/**
	 * 获取用户详情数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	@ResponseBody
	public UserInfoEntity userDetailData(@PathVariable Long id){
		UserInfoEntity userInfo = userInfoService.findById(id);
		if(userInfo!=null){
			return userInfo;
		}
		return null;
	}
	
	/**
	 * 修改用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String userUpdate(Long id,String username,String password){
		UserEntity user = userService.findById(id);
		if(user!=null){
			if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
				return "update fail:the username or  password can not empty !";
			}
			user.setUserName(username);
			user.setPassword(password);
			UserEntity updatedUser = userService.update(user);
			if(updatedUser!=null){
				return "success";
			}
		}
		return "update fail:the user can not find !";
	}
	
	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value = "/upload_avatar",method=RequestMethod.POST)
    public String  springUpload(Long avatarUserId,AvatarBean avatarBean,HttpServletRequest request) throws IllegalStateException, IOException{
    	if(avatarBean.getAvatarFile()!=null){
    		if(fileService.verify(FileTypeEnum.image, avatarBean.getAvatarFile())){
    			String fileUploadPath = fileService.uploadLocal(FileTypeEnum.image,avatarBean.getAvatarFile());
    			System.out.println("文件保存位置："+fileUploadPath);
    			UserInfoEntity userInfo = userInfoService.findById(avatarUserId);
    			if(userInfo!=null){
    				userInfo.setAvatarPath(fileUploadPath);
    				userInfoService.update(userInfo);
    			}
    		}
    	}
    return "admin/user/detail"; 
    }
}
