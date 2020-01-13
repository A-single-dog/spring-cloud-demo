package com.spring.cloud.user.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*
* @user zds
* @date 2020年1月13日下午3:12:58
**/

@Data
@ApiModel(value = "用户dto")
public class User {
	@ApiModelProperty(value = "用户ID")
	private Integer userId;
	@ApiModelProperty(value = "用户名")
	private String userName;
	@ApiModelProperty(value = "性别：1男， 2女")
	private Boolean sex;
	@ApiModelProperty(value = "生日")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;
}