package com.ykyd.eb.service.impl;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ykyd.eb.enums.FileTypeEnum;
import com.ykyd.eb.service.FileService;

/**
 * 文件服务实现类
 * @author zouyi
 */
@Service
public class FileServiceImpl implements FileService {
	
	/**允许上传的图片格式*/
	private final static String[] allowImageExtensions={
		"jpg","jpeg","bmp","gif","png","JPG","JPEG","BMP","GIF","PNG"};
	/**允许上传的文件格式*/
	private final static String[] allowFileExtensions ={
		"zip","rar","7z","doc","docx","xls","xlsx","ppt","pptx","ZIP",
		"RAR","7Z","DOC","DOCX","XLS","XLSX","PPT","PPTX"
	};
	
	/** servletContext */
    private ServletContext servletContext;
    
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
	
	@Override
	public boolean verify(FileTypeEnum fileType, MultipartFile multipartFile) {
		if (multipartFile == null) {
            return false;
        }
		String[] uploadExtensions = null;
		if (fileType == FileTypeEnum.image) {
            uploadExtensions = allowImageExtensions;
        } else if(fileType ==FileTypeEnum.file){
            uploadExtensions = allowFileExtensions;
        }
        if (uploadExtensions!=null && uploadExtensions.length>0) {
            return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
        }
		return false;
	}

	@Override
	public String uploadLocal(FileTypeEnum fileType, MultipartFile multipartFile) {
		if (multipartFile == null) {
            return null;
        }
		//上传根目录
		String uploadPath = "upload";
		if (fileType == FileTypeEnum.image) {
            uploadPath =uploadPath+"/image/";
        } else if(fileType == FileTypeEnum.file){
        	uploadPath =uploadPath+"/file/";
        }
		String destPath = uploadPath + UUID.randomUUID() + "."
                + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        File destFile = new File(servletContext.getRealPath(destPath));
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
			multipartFile.transferTo(destFile);
			return destPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
