package com.fall.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogStopWatch {

	@Pointcut("execution(* com.fall.spring..*Impl.*(..))")
	public void implPointCut() {}
	
	@Around("implPointCut()")
	public Object methodEstimate(ProceedingJoinPoint pp) throws Throwable {
		String methodName = pp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object obj = pp.proceed();
		stopWatch.stop();
		System.out.println(methodName + "() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)");
		return obj;
	}
}
