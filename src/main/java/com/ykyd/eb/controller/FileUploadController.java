package com.ykyd.eb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller("fileUploadController")
@RequestMapping("/fileUpload")
public class FileUploadController {
	
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
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException{
         long  startTime=System.currentTimeMillis();
         String path = System.getProperty("scheduleProject")+"springUpload";
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
//        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
//                request.getSession().getServletContext());
//        //检查form中是否有enctype="multipart/form-data"
//        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                	File uoloadPathFiles = new File(path);
                	if(!uoloadPathFiles.exists()){
                		uoloadPathFiles.mkdir();
                	}
                	path=path +"/"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
           
//        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        System.out.println("文件保存位置："+path);
    return "uploadfile"; 
    }
}
