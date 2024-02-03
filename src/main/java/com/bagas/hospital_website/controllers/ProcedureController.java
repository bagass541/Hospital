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

/**
 * Контроллер ProcedureController обрабатывает HTTP-запросы, связанные с процедурами (услугами) медицинского учреждения.
 * Адрес запросов начинается с "/procedures", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping(value = "/procedures")
public class ProcedureController {

	/**
	 * Сервис процедур для получения информации о доступных процедурах.
	 */
	@Autowired
	private ProcedureService procedureService;
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы с процедурами (услугами).
	 * 
	 * @return ModelAndView с данными о процедурах.
	 */
	@GetMapping
	public ModelAndView showServices() {
		List<Procedure> procedures = procedureService.getAllServices();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("procedures");
		modelAndView.addObject("procedures", procedures);
		return modelAndView;
	}
	
	/**
	 * Обрабатывает POST-запрос для удаления процедуры (услуги).
	 * 
	 * @param procedureId ИД процедуры, которую необходимо удалить.
	 * @return Строка с перенаправлением на страницу с процедурами после удаления.
	 */
	@PostMapping("/deleteProcedure")
	public String deleteProcedure(@RequestParam("procedureId") long procedureId) {
		procedureService.deleteProcedure(procedureId);
		return "redirect:/procedures";
	}
	
	/**
	 * Обрабатывает POST-запрос для добавления новой процедуры (услуги).
	 * 
	 * @param name Название процедуры.
	 * @param minutes Длительность процедуры в минутах.
	 * @param price Стоимость процедуры.
	 * @return Строка с перенаправлением на страницу с процедурами после добавления новой процедуры.
	 */
	@PostMapping("/addProcedure")
	public String addProcedure(@RequestParam("name") String name,
							   @RequestParam("minutes") int minutes,
							   @RequestParam("price") int price) {
		procedureService.addProcedure(name, minutes, price);
		return "redirect:/procedures";
	}
}
