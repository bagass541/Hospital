package com.bagas.hospital_website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер AdminPanelController обрабатывает HTTP-запросы, связанные с административной панелью приложения.
 * Адреса запросов начинаются с "/admin-panel", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelController {

	/**
	 * Обрабатывает GET-запросы для отображения административной панели.
	 * 
	 * @return  Имя представления "admin-panel", которое будет отображено пользователю.
	 */
	@GetMapping
	public String showAdminPanel() {
		return "admin-panel";
	}
}
