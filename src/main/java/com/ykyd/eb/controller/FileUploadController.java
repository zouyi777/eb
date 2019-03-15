package com.ykyd.eb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ykyd.eb.bean.AvatarBean;
import com.ykyd.eb.enums.FileTypeEnum;
import com.ykyd.eb.service.FileService;


@Controller("fileUploadController")
@RequestMapping("/fileUpload")
public class FileUploadController {
	
	@Resource
    private FileService fileService;
	
	/**
	 * 获取上传文件是视图
	 * @return
	 */
	@RequestMapping(value = "/springUpload",method=RequestMethod.GET)
	public String getFileUploadView(){
		return "uploadfile"; 
	}

	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value = "/springUpload",method=RequestMethod.POST)
    public String  springUpload(AvatarBean avatarBean,HttpServletRequest request) throws IllegalStateException, IOException{
    	if(avatarBean.getAvatarFile()!=null){
    		if(fileService.verify(FileTypeEnum.image, avatarBean.getAvatarFile())){
    			String fileUploadPath = fileService.uploadLocal(FileTypeEnum.image,avatarBean.getAvatarFile());
    			System.out.println("文件保存位置："+fileUploadPath);
    		}
    	}
    return "uploadfile"; 
    }
}
