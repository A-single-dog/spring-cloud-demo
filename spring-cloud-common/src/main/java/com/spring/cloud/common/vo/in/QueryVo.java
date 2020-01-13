package com.spring.cloud.common.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @user zds
 * @date 2020年1月13日下午5:03:52
 **/
@Data
public class QueryVo extends BaseVo {
	@ApiModelProperty(value = "当前页")
	private Integer pageNum = 1;// 当前页
	@ApiModelProperty(value = "每页显示总条数")
	private Integer pageSize = 10;// 每页显示总条数

	// 调用此方法可不使用分页，查询所有数据
	public void noPaging() {
		this.pageNum = null;
		this.pageSize = null;
	}

}
