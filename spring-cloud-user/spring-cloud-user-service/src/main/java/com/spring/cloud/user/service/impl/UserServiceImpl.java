package com.spring.cloud.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<User> findUsers() {

		return userMapper.findUsers();
	}

}
