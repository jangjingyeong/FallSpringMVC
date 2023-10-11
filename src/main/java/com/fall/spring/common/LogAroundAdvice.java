package com.fall.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAroundAdvice {

	public Object printLogAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("[BEFORE] 비즈니스 메소드 수행 전 처리할 내용");
		Object returnObj = pjp.proceed();
		System.out.println("[AFTER] 비즈니스 메소드 수행 후 처리할 내용");
		return returnObj;
	}
}
