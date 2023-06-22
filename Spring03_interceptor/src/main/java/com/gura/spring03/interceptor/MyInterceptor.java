package com.gura.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 	[인터셉터 만들기]
 * 
 * 1. HandlerInterceptor 인터페이스를 구현해서 만든다.
 * 2. servlet-content.xml에 bean 설정을 하고 interceptor 목록에 등록을 하고 맵핑을 해준다.
 */

public class MyInterceptor implements HandlerInterceptor{
	//controller 실행 이전에 호출되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//request scope에 메세지 담기
		request.setAttribute("msg", "인터셉터 발동");
		System.out.println("preHandle()");
		/*
		 * true를 리턴하면 계속 흐름을 이어가고 false를 리턴하면 이어가지 않는다.
		 */
		return true;
	}

	//controller 실행 이후에 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandler()");
	}
	//응답된 이후에 호출되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion()");
	}

}
