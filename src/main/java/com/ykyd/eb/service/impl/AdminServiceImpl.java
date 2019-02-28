package com.ykyd.eb.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.ykyd.eb.Principal;
import com.ykyd.eb.dao.AdminDao;
import com.ykyd.eb.entity.AdminEntity;
import com.ykyd.eb.service.AdminService;

/**
 * 管理员Service实现类
 * @author zouyi
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, Long> implements AdminService {
	
	@Resource
	private AdminDao adminDao;
	
	@Resource
    public void setBaseDao(AdminDao adminDao) {
        super.setBaseDao(adminDao);
    }
	@Override
	public AdminEntity findByUsername(String username) {
		return adminDao.findByUsername(username);
	}

	@Override
	public Long getCurrentId() {
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
	            return principal.getId();
	        }
		}
		return null;
	}


}
