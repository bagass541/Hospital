package com.bagas.hospital_website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.UserService;

/**
 * Контроллер PersonalAccountController обрабатывает HTTP-запросы, связанные с личным кабинетом пользователя.
 * Адрес запросов начинается с "/personal-account", определенных аннотацией @RequestMapping.
 */

@RequestMapping("/personal-account")
@Controller
public class PersonalAccountController {
	
	/**
	 * Сервис пользователя для получения информации о пользователях.
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Сервис записей на прием к врачу для получения информации о приемах пользователя.
	 */
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Обрабатывает GET-запрос для отображения личного кабинета пользователя.
	 * 
	 * @return ModelAndView с данными о личном кабинете и записях на прием пользователя.
	 */
	@GetMapping
	public ModelAndView showPersonalAccount() {	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("personal-account");
		modelAndView.addObject("fio", userService.getFio());
		modelAndView.addObject("number", userService.getNumber());
		modelAndView.addObject("appointments", appointmentService.getAllAppointmentsByUser());
		
		return modelAndView;
	}
	
	/**
	 * Обрабатывает POST-запрос для удаления записи на прием врача.
	 * 
	 * @param id ИД записи на прием, которую необходимо удалить.
	 * @return Строка с перенаправлением на страницу личного кабинета после удаления записи.
	 */
	@PostMapping("/deleteAppointment")
	public String deleteAppointment(@RequestParam("appointmentId") long id) {
		appointmentService.deleteAppointmentById(id);
		
		return "redirect:/personal-account";
	}
}
