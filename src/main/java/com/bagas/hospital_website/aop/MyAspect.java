package com.bagas.hospital_website.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Аспект MyAspect предоставляет советы (advice) для логирования входа и выхода из методов,
 * определенных в пакете "com.bagas.hospital_website.services" и его подпакетах.
 * Использует точки среза из класса Pointcuts для определения, к каким методам следует применять советы.
 */

@Aspect
@Component
@Slf4j
public class MyAspect {
	
	/**
	 * Совет "Before" для логирования входа в методы из пакета "com.bagas.hospital_website.services".
	 * 
	 * @param joinPoint Объект, содержащий информацию о вызываемом методе.
	 */
	@Before("Pointcuts.serviceMethods()")
	public void logMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		log.info("Entering method: " + methodName + " with arguments" + args);
	}
	
	/**
	 * Совет "AfterReturning" для логирования успешного выхода из методов из пакета "com.bagas.hospital_website.services".
	 * 
	 * @param joinPoint Объект, содержащий информацию о вызываемом методе.
	 * @param result Результат, возвращенный методом.
	 */
	@AfterReturning(pointcut = "Pointcuts.serviceMethods()", returning = "result")
	public void logMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		log.info("Exiting method: " + methodName + " with result: " + result);
	}
	
}
