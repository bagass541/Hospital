package com.bagas.hospital_website.controllers;

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
import com.bagas.hospital_website.repositories.DoctorRepository;
import com.bagas.hospital_website.services.DoctorService;

@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {

	@Autowired
	private DoctorService doctorService;
	
	// Test
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping
	public ModelAndView showAppointment() {
	//	List<Doctor> doctorList = doctorService.getAllDoctors();
		DoctorType[] doctorTypes = DoctorType.values();
		List<Doctor> doctors = doctorRepository.findByDoctorType(DoctorType.THERAPIST);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("make-appointment");
		modelAndView.addObject("doctorTypes", doctorTypes);
	//	modelAndView.addObject("doctorList", doctorList);
		
		return modelAndView;
	}
	
//	@PostMapping
//	public ModelAndView saveAppointment() {
//		
//	}
	
	// Test
	@GetMapping("/doctors")
	@ResponseBody
	public List<Doctor> getDoctorsByType(@RequestParam("type") String type) {
		System.out.println("getDoctorsByType");
		List<Doctor> doctors = doctorService.getDoctorsByType(type);
		doctors.stream().forEach(s -> System.out.println(s.getFio() + ": Service"));
		return doctors;
	}
	
}
