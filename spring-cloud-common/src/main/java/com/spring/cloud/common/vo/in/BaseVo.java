package com.spring.cloud.common.vo.in;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseVo implements Serializable {
//	@ApiModelProperty(value="当前登录用户id", required=true)
//	public Integer userId;
	@ApiModelProperty(value="用户登陆token", required=false)
    public String loginToken;
}
