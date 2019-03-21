package com.ykyd.eb.controller.admin;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.Page;
import com.ykyd.eb.Pageable;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.entity.UserInfoEntity;
import com.ykyd.eb.enums.FileTypeEnum;
import com.ykyd.eb.service.FileService;
import com.ykyd.eb.service.UserInfoService;
import com.ykyd.eb.service.UserService;
import com.ykyd.eb.vo.AvatarVo;
import com.ykyd.eb.vo.UserInfoVo;

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
	 * 获取用户详情视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String userDetail(){
		return "admin/user/detail";
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
    public Page<UserInfoVo> listUserPage(Integer pageNumber){
		Pageable pageable = new Pageable();
		pageable.setPageNumber(pageNumber);
		pageable.setPageSize(PAGE_SIZE);
		
		List<UserInfoVo> listUserInfoVo = new ArrayList<UserInfoVo>();
		//查询用户信息
		Page<UserInfoEntity> pageUserInfo = userInfoService.findPage(pageable);
		List<UserInfoEntity> listUserInfo= pageUserInfo.getDataList();
		//查询用户
		Page<UserEntity> pageUser = userService.findPage(pageable);
		List<UserEntity> listUser = pageUser.getDataList();
		for(int i=0;i<listUserInfo.size();i++){
			UserInfoVo userInfoVo = new UserInfoVo();
			userInfoVo.setUserInfo(listUserInfo.get(i));
			userInfoVo.setUser(listUser.get(i));
			listUserInfoVo.add(userInfoVo);
		}
        return new Page<UserInfoVo>(pageable,listUserInfoVo,pageUserInfo.getTotalCounts());
    }
	
	/**
	 * 删除单个用户
	 * @param Id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable Long id){
		UserInfoEntity  userInfoEntity  = userInfoService.findById(id);
		if(userInfoEntity!=null){
			Long userId = userInfoEntity.getUserId();
			userService.delete(userId);//先删除User实体
			userInfoService.delete(id);//再删除UserInfo实体
		}
		return "success";
	}
	
	
	/**
	 * 获取用户详情数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	@ResponseBody
	public UserInfoVo userDetailData(@PathVariable Long id){
		UserInfoEntity userInfo = userInfoService.findById(id);
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
	
	/**
	 * 修改用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String userUpdate(@RequestBody UserInfoVo userInfoVo){
		if(userInfoVo!=null){
			UserInfoEntity userInfo = userInfoVo.getUserInfo();
			if(userInfo!=null){
				UserInfoEntity userInfoEntity=userInfoService.findById(userInfo.getId());
				userInfoEntity.setNickName(userInfo.getNickName());
				userInfoEntity.setRealName(userInfo.getRealName());
				userInfoEntity.setIdentityNo(userInfo.getIdentityNo());
				userInfoEntity.setPhoneNo(userInfo.getPhoneNo());
				userInfoEntity.setEmail(userInfo.getEmail());
				userInfoEntity.setLastModifyDate(new Date());
				
				UserEntity user = userInfoVo.getUser();
				if(user!=null){
					UserEntity userEntity = userService.findById(userInfoEntity.getUserId());
					userEntity.setUserName(user.getUserName());
					userEntity.setPassword(user.getPassword());
					userEntity.setLastModifyDate(new Date());
					
					UserInfoEntity updatedUserInfo = userInfoService.update(userInfoEntity);
					UserEntity updatedUser = userService.update(userEntity);
					if(updatedUserInfo !=null && updatedUser!=null){
						return "success";
					}
				}
				
			}
		}
		return "update fail:the user can not find !";
	}
	
	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value = "/upload_avatar",method=RequestMethod.POST)
    public String  springUpload(Long avatarUserId,AvatarVo avatarVo,HttpServletRequest request) throws IllegalStateException, IOException{
    	if(avatarVo.getAvatarFile()!=null){
    		if(fileService.verify(FileTypeEnum.image, avatarVo.getAvatarFile())){
    			String fileUploadPath = fileService.uploadLocal(FileTypeEnum.image,avatarVo.getAvatarFile());
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
