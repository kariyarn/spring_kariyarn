package com.example.aop.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.aop.util.MemberDto;

@Aspect
@Component
public class MemberAspect {
	@Around("execution(com.example.aop.util.MemberDto get*(..))")
	public Object test(ProceedingJoinPoint joinpoint) throws Throwable{
		//aspect가 적용된 메소드를 수행하고 리턴되는 값을(참조값) 받아온다.
		Object obj = joinpoint.proceed();
		//원래 type으로 캐스팅
		MemberDto dto = (MemberDto)obj;
		//필드에 값을 넣어준다.
		dto.setNum(1);
		dto.setName("김구라");
		dto.setAddr("노량진");
		//joinPoint.proceed()메소드가 리턴한 참조값을 그대로 리턴해준다.
		return dto;
	}
}
