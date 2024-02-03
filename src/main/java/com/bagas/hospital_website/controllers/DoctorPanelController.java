package com.bagas.hospital_website.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Appointment;
import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.DoctorService;

/**
 * Контроллер DoctorPanelController обрабатывает HTTP-запросы, связанные с административной панелью врачей.
 * Адреса запросов начинаются с "/admin-panel/doctors", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping(value = "/admin-panel/doctors")
public class DoctorPanelController {
	
	/**
	 * Сервис для работы с данными о врачах.
	 */
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * Сервис для работы с записями на прием.
	 */
	@Autowired
	private AppointmentService appointmentService;
	
	/**
	 * Обрабатывает GET-запрос для отображения страницы административной панели врачей.
	 * 
	 * @return ModelAndView для отображения страницы и передачи модели с данными.
	 */
	@GetMapping
	public ModelAndView showDoctorsPanel() {
		List<Doctor> doctors = doctorService.getAllDoctorsOrderByDoctorTypeFio();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("doctors");
		modelAndView.addObject("doctors", doctors);
		modelAndView.addObject("doctorTypes", DoctorType.values());
		return modelAndView;
	}
	
	/**
	 * Обрабатывает POST-запрос для удаления врача по указанному ИД и перенаправляет пользователя.
	 * 
	 * @param id ИД врача.
	 * @return Строка перенаправления пользователя.
	 */
	@PostMapping("/deleteDoctor") 
	public String deleteDoctor(@RequestParam("doctorId") long id) {
		doctorService.deleteDoctorById(id);
		return "redirect:/admin-panel/doctors";
	}
	
	/**
	 * Обрабатывает POST-запрос для добавления нового врача и создания записей на прием для него.
	 * 
	 * @param doctorType Тип врача.
	 * @param fio ФИО врача.
	 * @param startWork Время начала рабочего дня врача.
	 * @param endWork Время окончания рабочего дня врача.
	 * @return Строка перенаправления пользователя.
	 */
	@PostMapping("/addDoctor")
	public String addDoctor(@RequestParam("selectType") String doctorType,
							@RequestParam("fio") String fio,
							@RequestParam("startWork") LocalTime startWork,
							@RequestParam("endWork") LocalTime endWork) {
		long idDoctor = doctorService.addDoctor(doctorType, fio, startWork, endWork);
		appointmentService.generateAppointmentsForWeek(idDoctor);
		
		return "redirect:/admin-panel/doctors";
	}
	
	/**
	 * Обрабатывает GET-запрос для отображения записей на прием для конкретного врача.
	 * 
	 * @param doctorId ИД врача.
	 * @return ModelAndView для отображения страницы и передачи модели с данными.
	 */
	@GetMapping("/{doctorId}")
	public ModelAndView showAppointmentsForDoctor(@PathVariable("doctorId") long doctorId) {
		LocalDate currDate = LocalDate.now();
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctorDate(doctorId, currDate);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appointments", appointments);
		modelAndView.addObject("currDate", currDate);
		modelAndView.setViewName("appointments");
		
		return modelAndView;
	}
}
