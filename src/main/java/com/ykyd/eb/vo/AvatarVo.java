package com.ykyd.eb.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;
/**
 * 头像VO
 * @author zouyi
 *
 */
public class AvatarVo implements Serializable {
	/**serialVersionUID*/
	private static final long serialVersionUID = -3273466111721607012L;

	/**头像路径*/
    private String avatarPath;

    /**头像文件 */
    private MultipartFile avatarFile;

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}
}
