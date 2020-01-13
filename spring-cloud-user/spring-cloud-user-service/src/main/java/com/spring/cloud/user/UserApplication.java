package com.spring.cloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 用户相关
 * @user zds
 * 2020年1月10日下午3:44:44
 */
@MapperScan(basePackages={"com.spring.cloud.user.mapper"})
@SpringBootApplication
@EnableEurekaClient
public class UserApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserApplication.class, args);
	}
}
