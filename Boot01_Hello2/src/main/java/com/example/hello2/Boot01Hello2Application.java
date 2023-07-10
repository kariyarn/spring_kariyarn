package com.example.hello2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.hello2.util.Remocon;

@SpringBootApplication
public class Boot01Hello2Application {

	public static void main(String[] args) {
		//run() 메소드가 리턴해주는 ApplicationContext 객체의 참조값을 변수에 담고
		ApplicationContext ctx=SpringApplication.run(Boot01Hello2Application.class, args);
		//해당 객체로부터 Car type 객체 얻어내기
		Car car1 = ctx.getBean(Car.class);
		car1.drive();
		
		//getBean()메소드를 다시 호출해도 객체를 하나만 생성한다.
		Car car2 = ctx.getBean(Car.class);
		car2.drive();
		
		if(car1 == car2) {
			System.out.println("car1과 car2는 같아요");
		}
		//스프링이 관리하는 객체 중에서 Remocon type의 참조값 찾아오기
		Remocon r1 = ctx.getBean(Remocon.class);
		r1.up();
		r1.down();
		//스프링이 관리하는 객체 중에서 myRemocon이라는 이름의 객체를 얻어와서 원리 type으로 casting해서 사용하기
		Remocon r2 = (Remocon)ctx.getBean("myRemocon");
		r2.up();
		r2.down();
	}
}
