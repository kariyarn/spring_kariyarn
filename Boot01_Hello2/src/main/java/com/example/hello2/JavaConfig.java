package com.example.hello2;

import org.springframework.context.annotation.Configuration;
/*
 * 어떤 객체를 spring이 생성해서 관리할 지 설정 (bean 설정)
 * 
 * xml문서로 설정하던 bean 설정을 class기반으로 한다.
 */
@Configuration
public class JavaConfig {
	public Car myCar() {
		System.out.println("myCar() 메소드 호출됨");
		Car c1=new Car();
		return c1;
	}
}
