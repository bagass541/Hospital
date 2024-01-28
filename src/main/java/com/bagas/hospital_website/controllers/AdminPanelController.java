package com.bagas.hospital_website.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.DoctorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping
	public String showAdminPanel() {
		return "admin-panel";
	}
	
	@GetMapping("/doctors")
	public ModelAndView showDoctorsPanel() {
		List<Doctor> doctors = doctorService.getAllDoctorsOrderByDoctorTypeFio();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("doctors");
		modelAndView.addObject("doctors", doctors);
		modelAndView.addObject("doctorTypes", DoctorType.values());
		return modelAndView;
	}
	
	@PostMapping("/doctors/deleteDoctor") 
	public String deleteDoctor(@RequestParam("doctorId") long id) {
		doctorService.deleteDoctorById(id);
		return "redirect:/admin-panel/doctors";
	}
	
	@PostMapping("/doctors/addDoctor")
	public String addDoctor(@RequestParam("selectType") String doctorType,
							@RequestParam("fio") String fio,
							@RequestParam("startWork") LocalTime startWork,
							@RequestParam("endWork") LocalTime endWork) {
		long idDoctor = doctorService.addDoctor(doctorType, fio, startWork, endWork);
		appointmentService.generateAppointmentsForWeek(idDoctor);
		
		return "redirect:/admin-panel/doctors";
	}
}
