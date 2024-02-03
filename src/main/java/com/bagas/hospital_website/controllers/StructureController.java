package com.bagas.hospital_website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.services.ChildInstitutionService;
import com.bagas.hospital_website.services.FilialService;
import com.bagas.hospital_website.services.HospitalService;
import com.bagas.hospital_website.services.PharmacyService;
import com.bagas.hospital_website.services.PolyclinicService;
import com.bagas.hospital_website.services.StomatologyService;

/**
 * Контроллер StructureController обрабатывает HTTP-запросы, связанные с отображением и управлением структурными элементами
 * в системе здравоохранения, такими как филиалы, поликлиники, больницы и др.
 * Адрес запросов начинается с "/structure", определенных аннотацией @RequestMapping.
 */

@RequestMapping(value ="/structure")
@Controller
public class StructureController {

	/**
	 * Сервис филиалов для получения информации о доступных филиалах.
	 */
	@Autowired
	private FilialService filialService;
	
	/**
	 * Сервис больниц для получения информации о доступных больницах.
	 */
	@Autowired
	private HospitalService hospitalService;
	
	/**
	 * Сервис аптек для получения информации о доступных аптеках.
	 */
	@Autowired
	private PharmacyService pharmacyService;
	
	/**
	 * Сервис поликлиник для получения информации о доступных поликлиниках.
	 */
	@Autowired
	private PolyclinicService polyclinicService;
	
	/**
	 * Сервис детских учреждений для получения информации о доступных детских учреждениях.
	 */
	@Autowired
	private ChildInstitutionService childInstitutionService;
	
	/**
	 * Сервис стоматологий для получения информации о доступных стоматологиях.
	 */
	@Autowired
	private StomatologyService stomatologyService;
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы с структурными элементами в зависимости от типа элемента.
	 * 
	 * @param elementType Тип структурного элемента (филиалы, поликлиники, больницы и т.д.).
	 * @return ModelAndView с данными о структурных элементах и их типе.
	 */
	@GetMapping("/{elementType}")
	public ModelAndView showStructureElement(@PathVariable("elementType") String elementType) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("structure-element");
		String heading = "";
		
		switch(elementType) {
			case "filials": 
				modelAndView.addObject("structureElements", filialService.getAllFilials());
				heading = "Филиалы ГЦГКП";
				break;
			case "polyclinics":
				modelAndView.addObject("structureElements", polyclinicService.getAllPolyclinics());
				heading = "Поликлиники";
				break;
			case "hospitals":
				modelAndView.addObject("structureElements", hospitalService.getAllHospitals());
				heading = "Больницы";
				break;
			case "child-institutions":
				modelAndView.addObject("structureElements", childInstitutionService.getAllChildInstitutions());
				heading = "Детские учреждения";
				break;
			case "stomatologies":
				modelAndView.addObject("structureElements", stomatologyService.getAllStomatologies());
				heading = "Стоматологии";
				break;
			case "pharmacies":
				modelAndView.addObject("structureElements", pharmacyService.getAllPharmacies());
				heading = "Аптеки г. Гомеля";
				break;
		}
		modelAndView.addObject("h1Name", heading);
		return modelAndView;
	}
	
	/**
	 * Обрабатывает POST-запрос для удаления структурного элемента.
	 * 
	 * @param id ИД структурного элемента, который необходимо удалить.
	 * @param elementType Тип структурного элемента (филиалы, поликлиники, больницы и т.д.).
	 * @return Строка перенаправления на страницу со структурными элементами данного типа.
	 */
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
	
	/**
	 * Обрабатывает POST-запрос для добавления нового структурного элемента.
	 * 
	 * @param name Имя нового структурного элемента.
	 * @param elementType Тип структурного элемента (филиалы, поликлиники, больницы и т.д.).
	 * @return Строка перенаправления на страницу со структурными элементами данного типа.
	 */
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
