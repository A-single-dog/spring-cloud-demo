package com.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关启动类
 * 
 * @user zds
 * @date 2020年1月10日下午4:24:02
 **/
@SpringBootApplication
// 简单理解为@EnableZuulServer的增强版，当Zuul与Eureka、Ribbon等组件配合使用时，我们使用@EnableZuulProxy
// 开启zuul的网关功能，他是一个组合注解，集成了eureka客户端注解。
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZuulApplication.class, args);
	}
}
