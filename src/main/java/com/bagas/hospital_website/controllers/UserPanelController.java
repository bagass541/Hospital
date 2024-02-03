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

/**
 * Контроллер UserPanelController обрабатывает HTTP-запросы, связанные с управлением пользователями в административной панели.
 * Адрес запросов начинается с "/admin-panel/users", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping(value = "/admin-panel/users")
public class UserPanelController {

	/**
	 * Сервис пользователей для получения информации о доступных пользователях.
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Сервис ролей для получения информации о доступных ролях.
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы с управлением пользователями.
	 * 
	 * @return ModelAndView с данными о пользователях и ролях.
	 */ 
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
	
	/**
	 * Обрабатывает POST-запрос для удаления пользователя.
	 * 
	 * @param id ИД пользователя, который необходимо удалить.
	 * @return Строка с перенаправлением на страницу управления пользователями после удаления.
	 */
	@PostMapping("/deleteUser") 
	public String deleteUser(@RequestParam("userId") long id) {
		userService.deleteUserById(id);
		return "redirect:/admin-panel/users";
	}
	
	/**
	 * Обрабатывает POST-запрос для добавления нового пользователя.
	 * 
	 * @param username Логин нового пользователя.
	 * @param password Пароль нового пользователя.
	 * @param fio ФИО нового пользователя.
	 * @param number Номер телефона нового пользователя.
	 * @param authority Роль нового пользователя.
	 * @return Строка с перенаправлением на страницу управления пользователями после добавления.
	 */
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
