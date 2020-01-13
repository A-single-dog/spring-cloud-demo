package com.spring.cloud.user.service;

import com.spring.cloud.common.util.PageBean;
import com.spring.cloud.common.vo.in.QueryVo;
import com.spring.cloud.user.domain.User;

/**
 *
 * @user zds
 * @date 2020年1月13日上午11:15:00
 **/
public interface UserService {

	PageBean<User> findUsers(QueryVo vo);
}

