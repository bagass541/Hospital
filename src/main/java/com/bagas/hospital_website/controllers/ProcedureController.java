package com.bagas.hospital_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Procedure;
import com.bagas.hospital_website.services.ProcedureService;

@Controller
@RequestMapping(value = "/procedures")
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;
	
	@GetMapping
	public ModelAndView showServices() {
		List<Procedure> procedures = procedureService.getAllServices();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("procedures");
		modelAndView.addObject("procedures", procedures);
		return modelAndView;
	}
	
	@PostMapping("/deleteProcedure")
	public String deleteProcedure(@RequestParam("procedureId") long procedureId) {
		procedureService.deleteProcedure(procedureId);
		return "redirect:/procedures";
	}
	
	@PostMapping("/addProcedure")
	public String addProcedure(@RequestParam("name") String name,
							   @RequestParam("minutes") int minutes,
							   @RequestParam("price") int price) {
		procedureService.addProcedure(name, minutes, price);
		return "redirect:/procedures";
	}
}
