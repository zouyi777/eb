package com.ykyd.eb.service;

import org.springframework.web.multipart.MultipartFile;

import com.ykyd.eb.enums.FileTypeEnum;

/**
 * 文件服务
 * @author zouyi
 */
public interface FileService {
	
	/**
	 * 文件验证
	 *@param fileType 文件类型
	 *@param multipartFile 上传文件
	 *@return 文件验证是否通过
	 */
	boolean verify(FileTypeEnum fileType, MultipartFile multipartFile);
	
	/**
     * 文件上传至本地
     * 
     * @param fileType
     *            文件类型
     * @param multipartFile
     *            上传文件
     * @return 路径
     */
    String uploadLocal(FileTypeEnum fileType, MultipartFile multipartFile);

}
