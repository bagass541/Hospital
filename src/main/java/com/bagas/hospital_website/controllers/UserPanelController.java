package com.bagas.hospital_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Role;
import com.bagas.hospital_website.models.User;
import com.bagas.hospital_website.services.RoleService;
import com.bagas.hospital_website.services.UserService;

@Controller
@RequestMapping(value = "/admin-panel/users")
public class UserPanelController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ModelAndView showUsersPanel() {
		List<User> users = userService.getAllUsersOrderByFio();
		List<Role> roles = roleService.getAllRoles();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("users", users);
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("users");
		
		return modelAndView;
	}
	
	@PostMapping("/deleteUser") 
	public String deleteUser(@RequestParam("userId") long id) {
		userService.deleteUserById(id);
		return "redirect:/admin-panel/users";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestParam("username") String username,
						  @RequestParam("password") String password,
						  @RequestParam("fio") String fio,
						  @RequestParam("numberPhone") String number,
						  @RequestParam("role") String authority) {
		userService.addUser(username, password, fio, number, authority);
		return "redirect:/admin-panel/users";
	}
}
