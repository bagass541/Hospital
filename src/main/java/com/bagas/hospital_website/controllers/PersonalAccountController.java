package com.bagas.hospital_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Appointment;
import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.UserService;

@RequestMapping("/personal-account")
@Controller
public class PersonalAccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping
	public ModelAndView showPersonalAccount() {	
		List<Appointment> list = appointmentService.getAllAppointmentsByUser();
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("personal-account");
		modelAndView.addObject("fio", userService.getFio());
		modelAndView.addObject("number", userService.getNumber());
		modelAndView.addObject("appointments", appointmentService.getAllAppointmentsByUser());
		
		return modelAndView;
	}
	
	@PostMapping("/deleteAppointment")
	public String deleteAppointment(@RequestParam("appointmentId") long id) {
		appointmentService.deleteAppointmentById(id);
		
		return "redirect:/personal-account";
	}
}
