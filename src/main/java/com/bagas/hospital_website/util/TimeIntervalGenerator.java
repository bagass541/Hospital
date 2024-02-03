package com.bagas.hospital_website.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Класс TimeIntervalGenerator предоставляет функциональность для генерации временных интервалов
 * с указанным шагом между начальным и конечным временем.
 * Интервалы генерируются с шагом в 15 минут.
 */

@Component
public class TimeIntervalGenerator {

	/**
	 * Константа, представляющая шаг временных интервалов в минутах.
	 */
	private final int  INTERVAL_MINUTE = 15;
	
	/**
	 * Генерирует временные интервалы с указанным шагом между начальным и конечным временем.
	 * 
	 * @param start Начальное время интервалов.
	 * @param end Конечное время интервалов.
	 * @return Список временных интервалов между указанными временами с заданным шагом.
	 */
	public List<LocalTime> generateIntervals(LocalTime start, LocalTime end) {
		List<LocalTime> intervals = new ArrayList<>();
		
		LocalTime currentTime = start;
		
		while(currentTime.isBefore(end)) {
			intervals.add(currentTime);
			currentTime = currentTime.plusMinutes(INTERVAL_MINUTE);
		}
		
		return intervals;
	}
}
