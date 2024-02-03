package com.bagas.hospital_website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер HomeController обрабатывает HTTP-запросы, связанные с основными страницами сайта.
 * Адреса запросов начинаются с "/", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
	/**
	 * Обрабатывает GET-запрос для отображения главной страницы сайта.
	 * 
	 * @return Строка с именем представления для главной страницы.
	 */
	@GetMapping
	public String showHomePage() {
		return "home";
	}
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы со структурой организации.
	 *  
	 * @return Строка с именем представления для страницы со структурой организации.
	 */
	@GetMapping("/structure")
	public String showListStructure() {
		return "structure";
	}
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы "О нас".
	 * 
	 * @return Строка с именем представления для страницы "О нас".
	 */
	@GetMapping("/about-us") 
	public String showAboutUs() {
		return "about-us";
	}
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы с контактной информацией.
	 * 
	 * @return Строка с именем представления для страницы с контактной информацией.
	 */
	@GetMapping("/contacts")
	public String showContacts() {
		return "contacts";
	}
	
}
