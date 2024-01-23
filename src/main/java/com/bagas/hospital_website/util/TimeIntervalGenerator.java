package com.bagas.hospital_website.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TimeIntervalGenerator {

	private final int  INTERVAL_MINUTE = 15;
	
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
