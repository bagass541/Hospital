package com.bagas.hospital_website.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class MyAspect {

	@Before("Pointcuts.serviceMethods()")
	public void logMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		log.info("Entering method: " + methodName + " with arguments" + args);
	}
	
	@AfterReturning(pointcut = "Pointcuts.serviceMethods()", returning = "result")
	public void logMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		log.info("Exiting method: " + methodName + " with result: " + result);
	}
	
}
