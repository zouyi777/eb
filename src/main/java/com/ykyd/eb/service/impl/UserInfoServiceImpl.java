package com.ykyd.eb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ykyd.eb.dao.UserInfoDao;
import com.ykyd.eb.entity.UserInfoEntity;
import com.ykyd.eb.service.UserInfoService;

/**
 * 用户信息Service接口实现类
 * @author zouyi
 *
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoEntity, Long>
		implements UserInfoService {
	
	@Resource
    public void setBaseDao(UserInfoDao userInfoDao) {
        super.setBaseDao(userInfoDao);
    }
}
