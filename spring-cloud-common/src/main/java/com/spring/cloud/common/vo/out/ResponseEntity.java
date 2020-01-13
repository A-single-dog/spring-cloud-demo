package com.spring.cloud.common.vo.out;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应Json报文包装
 */
@Data
@ApiModel("响应结果")
public class ResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int SUCCESS = 200;
	private static final String SUCCESS_MESSAGE = "操作成功";
	private static final int ERROR = 500;
	private static final String ERROR_MESSAGE = "操作失败";
	@ApiModelProperty(required = true, value = "响应时的时间戳")
	private long curtime = System.currentTimeMillis();
	@ApiModelProperty(required = true, value = "响应的错误代码")
	private Integer code;
	@ApiModelProperty(required = true, value = "响应的消息")
	private String message;
	@ApiModelProperty(required = false, value = "响应的业务数据")
	private T data;

	public static <T> ResponseEntity<T> success() {
		ResponseEntity<T> entity = new ResponseEntity<T>();
		entity.setCode(SUCCESS);
		entity.setMessage(SUCCESS_MESSAGE);
		return entity;
	}

	public static <T> ResponseEntity<T> success(T date) {
		ResponseEntity<T> entity = success();
		entity.setData(date);
		return entity;
	}



	public static <T> ResponseEntity<T> error() {
		ResponseEntity<T> entity = new ResponseEntity<T>();
		entity.setCode(ERROR);
		entity.setMessage(ERROR_MESSAGE);
		return entity;
	}

	public static <T> ResponseEntity<T> error(Integer errorCode) {
		ResponseEntity<T> entity = error();
		entity.setCode(errorCode);
		return entity;
	}

	public static <T> ResponseEntity<T> error(String message) {
		ResponseEntity<T> entity = error();
		entity.setMessage(message);
		return entity;
	}

	public static <T> ResponseEntity<T> error(Integer errorCode, String message) {
		ResponseEntity<T> entity = error();
		entity.setCode(errorCode);
		entity.setMessage(message);
		return entity;
	}
}
