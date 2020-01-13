package com.spring.cloud.user.service;

import java.util.List;

import com.spring.cloud.user.domain.User;

/**
 *
 * @user zds
 * @date 2020年1月13日上午11:15:00
 **/
public interface UserService {

	List<User> findUsers();
}

