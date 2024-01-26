package com.bagas.hospital_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.structure.ChildInstitution;
import com.bagas.hospital_website.models.structure.Filial;
import com.bagas.hospital_website.models.structure.Hospital;
import com.bagas.hospital_website.models.structure.Pharmacy;
import com.bagas.hospital_website.models.structure.Polyclinic;
import com.bagas.hospital_website.models.structure.Stomatology;
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
		String heading = "Филиалы ГЦГКП";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", filials);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@GetMapping("/polyclinics")
	public ModelAndView showPolyclinics() {
		List<Polyclinic> polyclinics = polyclinicService.getAllPolyclinics();
		String heading = "Поликлиники";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", polyclinics);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@GetMapping("/hospitals")
	public ModelAndView showHospitals() {
		List<Hospital> hospitals = hospitalService.getAllHospitals();
		String heading = "Больницы";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", hospitals);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@GetMapping("/child-institutions")
	public ModelAndView showChildInstitutions() {
		List<ChildInstitution> childInstitutions = childInstitutionService.getAllChildInstitutions();
		String heading = "Детские учреждения";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", childInstitutions);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@GetMapping("/stomatologies")
	public ModelAndView showStomatologies() {
		List<Stomatology> stomatologies = stomatologyService.getAllStomatologies();
		String heading = "Стоматологии";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", stomatologies);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@GetMapping("/pharmacies")
	public ModelAndView showPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		String heading = "Аптеки г. Гомеля";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		modelAndView.addObject("structureElements", pharmacies);
		modelAndView.addObject("h1Name", heading);
		
		return modelAndView;
	}
	
	@PostMapping("/{elementType}/deleteElement")
	public String deleteElement(@RequestParam("structureElementId") long id, @PathVariable("elementType") String elementType) {
		switch (elementType) {
			case "filials" -> filialService.deleteFilialById(id);
			case "polyclinics" -> polyclinicService.deletePolyclinicById(id);
			case "hospitals" -> hospitalService.deleteHospitalById(id);
			case "child-institutions" -> childInstitutionService.deleteChildInstitutionById(id);
			case "stomatologies" -> stomatologyService.deleteStomatologyById(id);
			case "pharmacies" -> pharmacyService.deletePharmacyById(id);
		}
		
		return "redirect:/structure/" + elementType;
	}
	
	@PostMapping("/{elementType}/addElement")
	public String addElement(@RequestParam("name") String name, @PathVariable("elementType") String elementType) {
		switch (elementType) {
			case "filials" -> filialService.addFilial(name);
			case "polyclinics" -> polyclinicService.addPolyclinic(name);
			case "hospitals" -> hospitalService.addHospital(name);
			case "child-institutions" -> childInstitutionService.addChildInstitution(name);
			case "stomatologies" -> stomatologyService.addStomatology(name);
			case "pharmacies" -> pharmacyService.addPharmacy(name);
		}	
		
		return "redirect:/structure/" + elementType;
	}
	
}
