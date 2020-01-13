package com.spring.cloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.common.util.PageBean;
import com.spring.cloud.common.vo.in.QueryVo;
import com.spring.cloud.common.vo.out.ResponseEntity;
import com.spring.cloud.user.domain.User;
import com.spring.cloud.user.service.UserService;

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

	@RequestMapping(value = "/findUserList", method = RequestMethod.POST)
	public ResponseEntity<PageBean<User>> findUserList(@RequestBody QueryVo vo) {

		return ResponseEntity.success(userService.findUsers(vo));
	}
}
