package com.bagas.hospital_website.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Класс Pointcuts предоставляет точки среза (pointcuts) для использования в аспектах.
 */

public class Pointcuts {

	/**
	 * Точка среза "serviceMethods" для всех методов в пакете "com.bagas.hospital_website.services" и его подпакетах.
	 */
	@Pointcut("execution(* com.bagas.hospital_website.services.*.*(..))")
	public void serviceMethods() {}
}
