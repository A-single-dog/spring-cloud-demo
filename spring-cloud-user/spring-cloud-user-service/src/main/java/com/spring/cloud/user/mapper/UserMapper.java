package com.spring.cloud.user.mapper;

import java.util.List;

import com.spring.cloud.user.domain.User;

public interface UserMapper {
	
	List<User> findUsers();
	
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}