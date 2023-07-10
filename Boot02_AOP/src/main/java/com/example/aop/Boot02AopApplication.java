package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.aop.util.MemberDto;
import com.example.aop.util.MemberService;
import com.example.aop.util.Messenger;
import com.example.aop.util.WritingUtil;
/*
 * @SpringBottApplication 어노테이션이 붙어있는 main메소드가 존재하는 이 패키지를 포함해서
 * 
 * 하위의 모든 패키지에 component scan이 자동으로 일어난다.
 */
@SpringBootApplication
public class Boot02AopApplication {

	public static void main(String[] args) {
		//spring bean 컨테이너라고 생각하면 된다.
		ApplicationContext ctx=SpringApplication.run(Boot02AopApplication.class, args);
		
		//spring이 관리하는 bean 들 중에서 writingUtil type 객체의 참조값 얻어오기
		WritingUtil util = ctx.getBean(WritingUtil.class);
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		
		Messenger m1=ctx.getBean(Messenger.class);
		String msg = m1.getMessage();
		System.out.println("Messenger 객체로부터 받은 메세지 : "+msg);
		
		m1.sendGreeting("안녕하세요");
		m1.sendGreeting("잘 가세요");
		m1.sendGreeting("또 만나요");
		
		MemberService mem1=ctx.getBean(MemberService.class);
		MemberDto dto = mem1.getMember(1);
		System.out.println(dto.getNum());
		System.out.println(dto.getName());
		System.out.println(dto.getAddr());
		
	}

}
