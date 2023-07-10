package com.example.hello2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 * 어떤 객체를 spring이 생성해서 관리할 지 설정 (bean 설정)
 * 
 * xml문서로 설정하던 bean 설정을 class기반으로 한다.
 */

import com.example.hello2.util.Remocon;
import com.example.hello2.util.RemoconImpl;
import com.example.hello2.util.TvRemocon;
@Configuration
public class JavaConfig {
	/*
	 * 이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록 한다.
	 * 아래의 메소드는 xml문서에서 <bean id="myCar" class="com.example.demo.Car"/> 와 같다.
	 */
	@Bean
	public Car myCar() {//method의 이름이 bean의 이름(id)역할을 한다.
		System.out.println("myCar() 메소드 호출됨");
		Car c1=new Car();
		return c1;
	}
	
	//Remocon 인터페이스 type이 spring이 관리하는 bean이 되도록 설정해 보세요
	
	@Bean
	public Remocon myRemocon() {
		Remocon r1 = new RemoconImpl();
		return r1;
	}
	
	@Bean
	public Remocon tvRemocon() {
		Remocon r1 = new TvRemocon();
		return r1;
	}
}
