package com.bagas.hospital_website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value ="/structure")
@Controller
public class StructureController {

	
	@GetMapping("/filials")
	public String showFilials() {
		return "list-structure/filials";
	}
	
	@GetMapping("/polyclinics")
	public String showPolyclinics() {
		return "list-structure/polyclinics";
	}
	
	@GetMapping("/hospitals")
	public String showHospitals() {
		return "list-structure/hospitals";
	}
	
	@GetMapping("/child-institutions")
	public String showChildInstitutions() {
		return "list-structure/child-institutions";
	}
	
	@GetMapping("/stomatologies")
	public String showStomatologies() {
		return "list-structure/stomatologies";
	}
	
	@GetMapping("/pharmacies")
	public String showPharmacies() {
		return "list-structure/pharmacies";
	}
	
}
