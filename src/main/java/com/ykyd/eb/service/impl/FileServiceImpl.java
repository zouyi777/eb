package com.ykyd.eb.service.impl;

import org.springframework.web.multipart.MultipartFile;

import com.ykyd.eb.enums.FileTypeEnum;
import com.ykyd.eb.service.FileService;

/**
 * 文件服务实现类
 * @author zouyi
 */
public class FileServiceImpl implements FileService {
	
	private final static String[] allowImageExtensions={"jpg","jpeg,bmp,gif,png,JPG,JPEG,BMP,GIF,PNG};

	@Override
	public boolean verify(FileTypeEnum fileType, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String uploadLocal(FileTypeEnum fileType, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
