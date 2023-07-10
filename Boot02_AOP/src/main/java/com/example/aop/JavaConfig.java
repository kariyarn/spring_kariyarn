package com.example.aop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.aop.aspect.MessengerAspect;
import com.example.aop.util.MemberService;
import com.example.aop.util.Messenger;


@Configuration
@EnableAspectJAutoProxy
public class JavaConfig {
	
	@Bean
	public MessengerAspect msa() {
		return new MessengerAspect();
	}
	
	//Messenger 객체가 spring 이 관리하는 bean 이 될수 있도록!
	@Bean
	public Messenger myMessenger() {
		return new Messenger();
	}
	
	//MemberService를 Bean으로 만들기
	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
}