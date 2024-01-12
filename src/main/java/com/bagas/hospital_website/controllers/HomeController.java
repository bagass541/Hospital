package com.bagas.hospital_website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
	@GetMapping
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/services")
	public String showServices() {
		return "services";
	}
	
	@GetMapping("/structure")
	public String showListStructure() {
		return "structure";
	}
	
	@GetMapping("/about-us") 
	public String showAboutUs() {
		return "about-us";
	}
	
	@GetMapping("/contacts")
	public String showContacts() {
		return "contacts";
	}
	
	@GetMapping("/personal-account")
	public String showPersonalAccount() {
		return "personal-account";
	}

}
