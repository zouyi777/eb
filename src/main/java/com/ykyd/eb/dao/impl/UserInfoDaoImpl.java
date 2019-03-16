package com.ykyd.eb.dao.impl;

import org.springframework.stereotype.Repository;

import com.ykyd.eb.dao.UserInfoDao;
import com.ykyd.eb.entity.UserInfoEntity;

/**
 * 用户信息Dao实现类
 * @author zouyi
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfoEntity, Long> implements UserInfoDao {

}
