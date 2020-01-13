package com.spring.cloud.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spring.cloud.common.util.PageBean;
import com.spring.cloud.common.vo.in.QueryVo;
import com.spring.cloud.user.domain.User;
import com.spring.cloud.user.mapper.UserMapper;
import com.spring.cloud.user.service.UserService;

/**
 *
 * @user zds
 * @date 2020年1月13日上午11:15:19
 **/

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public PageBean<User> findUsers(QueryVo vo) {

		Page<User> pager = PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
		userMapper.findUsers();
		return new PageBean<User>(pager);
	}

}
