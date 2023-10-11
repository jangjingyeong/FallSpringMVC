package com.fall.spring.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAfterAdvice {
	
	@Pointcut("execution(* com.fall.spring..*Impl.*(..))")
	public void implPointCut() {}
	
	@After("implPointCut()")
	public void printLogAfter() {
		System.out.println("[공통로그 - LogAdvice] 비즈니스 로직 수행 후 동작");
	}
	
}
