package com.bagas.hospital_website.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

	@Pointcut("execution(* com.bagas.hospital_website.services.*.*(..))")
	public void serviceMethods() {}
}
