package com.spring.cloud.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.common.vo.out.ResponseEntity;
import com.spring.cloud.user.domain.User;
import com.spring.cloud.user.service.UserService;
import com.spring.cloud.user.util.BeanUtil;
import com.spring.cloud.user.vo.out.UserDto;

/**
 * 用户类
 * 
 * @user zds
 * @date 2020年1月10日下午4:03:28
 **/
@RequestMapping(value = "user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/findUserList", method = RequestMethod.GET)
	public ResponseEntity<List<UserDto>> findUserList() {

		List<User> list = userService.findUsers();
		List<UserDto> dtos = BeanUtil.copyList(list, UserDto.class);
		return ResponseEntity.success(dtos);
	}
}
