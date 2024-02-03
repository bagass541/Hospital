package com.bagas.hospital_website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер LoginController обрабатывает HTTP-запросы, связанные с аутентификацией и отображением страницы входа.
 */

@Controller
public class LoginController {

	/**
	 * Обрабатывает GET-запрос для отображения страницы входа.
	 * 
	 * @return Строка с именем представления для страницы входа.
	 */
	@GetMapping("/sign")
	public String showLoginPage() {
		return "login";
	}
}
