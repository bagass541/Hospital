package com.bagas.hospital_website.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.DoctorService;

@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public ModelAndView showAppointment() {
		DoctorType[] doctorTypes = DoctorType.values();
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("make-appointment");
		modelAndView.addObject("doctorTypes", doctorTypes);
		
		return modelAndView;
	}
	
	@GetMapping("/doctors")
	@ResponseBody
	public List<Doctor> getDoctorsByType(@RequestParam("type") String type) {
		List<Doctor> doctors = doctorService.getDoctorsByType(type);
		return doctors;
	}
	
	@GetMapping("/dates")
	@ResponseBody
	public List<LocalDate> getAvailableDates(@RequestParam("doctorId") String doctorId) {
		return appointmentService.getAvailableDates(Long.parseLong(doctorId));
	}
	
	@GetMapping("/times")
	@ResponseBody
	public List<LocalTime> getAvailableTimes(@RequestParam("doctorId") String doctorId,
											 @RequestParam("date") String date) {
	List<LocalTime> times = appointmentService.getAvailableTime(Long.parseLong(doctorId), LocalDate.parse(date));
	return times;
	}
	
	@PostMapping
	public String addAppointment(@RequestParam("doctorSelect") long doctorId,
									   @RequestParam("dateSelect") String date,
									   @RequestParam("timeSelect") String time) {
		appointmentService.makeAppointment(doctorId, LocalDate.parse(date), LocalTime.parse(time));
		
		return "redirect:/";
	}
}
