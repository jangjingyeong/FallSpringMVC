package com.fall.spring.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

	@Pointcut("execution(* com.fall.spring..*Impl.*(..))")
	public void implPointCut() {}
	
	@Before("implPointCut()")
	public void printLog() {
		System.out.println("[공통로그 - LogAdvice] 비즈니스 로직 수행 전 동작");
	}
}
