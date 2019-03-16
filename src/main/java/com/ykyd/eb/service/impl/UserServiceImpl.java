package com.ykyd.eb.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ykyd.eb.dao.UserDao;
import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.UserService;
/**
 * UserService 实现类
 * @author zouyi
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }
	@Override
	public UserEntity findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	@Override
	public Long getCurrentId() {
		return null;
	}

}
