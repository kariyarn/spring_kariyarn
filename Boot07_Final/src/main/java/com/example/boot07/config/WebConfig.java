package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot07.interceptor.LoginInterceptor;
/*
 * [Spring MVC 관련 설정]
 * 
 * 1. WebMvcConfigurer 인터페이스를 구현한다.
 * 2. 설정에 필요한 메소드만 오버라이딩한다.
 * 		주로 Resource Handler, Interceptor, view Page 관련 설정을 여기에서 한다.
 * 3. 설정에 관련된 클래스에는 @Configuration 어노테이션을 붙여야 한다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired LoginInterceptor loginInter;
	
	//Interceptor를 추가할 때 오버라이드 하는 메소드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//메소드의 인자로 전달되는 InterceptorRegistry 객체를 이용해서 Interceptor를 등록하면 된다.		
		registry.addInterceptor(loginInter)
			.addPathPatterns("/users/*")
			.excludePathPatterns("/users/loginform")
			.excludePathPatterns("/users/login")
			.excludePathPatterns("/users/signup")
			.excludePathPatterns("/users/signup_form");
	}
	
	//webapp/resources 폴더 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
