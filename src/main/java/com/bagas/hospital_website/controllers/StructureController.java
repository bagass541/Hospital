package com.bagas.hospital_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.ChildInstitution;
import com.bagas.hospital_website.models.Filial;
import com.bagas.hospital_website.models.Hospital;
import com.bagas.hospital_website.models.Pharmacy;
import com.bagas.hospital_website.models.Polyclinic;
import com.bagas.hospital_website.models.Stomatology;
import com.bagas.hospital_website.services.ChildInstitutionService;
import com.bagas.hospital_website.services.FilialService;
import com.bagas.hospital_website.services.HospitalService;
import com.bagas.hospital_website.services.PharmacyService;
import com.bagas.hospital_website.services.PolyclinicService;
import com.bagas.hospital_website.services.StomatologyService;

@RequestMapping(value ="/structure")
@Controller
public class StructureController {

	@Autowired
	private FilialService filialService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private PolyclinicService polyclinicService;
	
	@Autowired
	private ChildInstitutionService childInstitutionService;
	
	@Autowired
	private StomatologyService stomatologyService;
	
	@GetMapping("/filials")
	public ModelAndView showFilials() {
		List<Filial> filials = filialService.getAllFilials();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/filials");
		modelAndView.addObject("filialList", filials);
		
		return modelAndView;
	}
	
	@GetMapping("/polyclinics")
	public ModelAndView showPolyclinics() {
		List<Polyclinic> polyclinics = polyclinicService.getAllPolyclinics();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/polyclinics");
		modelAndView.addObject("polyclinicList", polyclinics);
		
		return modelAndView;
	}
	
	@GetMapping("/hospitals")
	public ModelAndView showHospitals() {
		List<Hospital> hospitals = hospitalService.getAllHospitals();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/hospitals");
		modelAndView.addObject("hospitalList", hospitals);
		
		return modelAndView;
	}
	
	@GetMapping("/child-institutions")
	public ModelAndView showChildInstitutions() {
		List<ChildInstitution> childInstitutions = childInstitutionService.getAllChildInstitutions();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/child-institutions");
		modelAndView.addObject("childInstitutionList", childInstitutions);
		
		return modelAndView;
	}
	
	@GetMapping("/stomatologies")
	public ModelAndView showStomatologies() {
		List<Stomatology> stomatologies = stomatologyService.getAllStomatologies();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/stomatologies");
		modelAndView.addObject("stomatologyList", stomatologies);
		
		return modelAndView;
	}
	
	@GetMapping("/pharmacies")
	public ModelAndView showPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-structure/pharmacies");
		modelAndView.addObject("pharmacyList", pharmacies);
		
		return modelAndView;
	}
	
}
