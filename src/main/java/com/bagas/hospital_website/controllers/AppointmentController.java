package com.bagas.hospital_website.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.services.AppointmentService;
import com.bagas.hospital_website.services.DoctorService;

/**
 * Контроллер AppointmentController обрабатывает HTTP-запросы, связанные с записью на прием к врачу.
 * Адреса запросов начинаются с "/appointment", определенных аннотацией @RequestMapping.
 */

@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {

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
	 * Обрабатывает GET-запрос для отображения страницы записи на прием.
	 * 
	 * @return ModelAndView для отображения страницы и передачи модели с данными.
	 */
	@GetMapping
	public ModelAndView showAppointment() {
		DoctorType[] doctorTypes = DoctorType.values();
			
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("make-appointment");
		modelAndView.addObject("doctorTypes", doctorTypes);
		
		return modelAndView;
	}
	
	/**
	 * Обрабатывает GET-запрос для получения списка врачей по указанному типу.
	 * 
	 * @param type Тип врача.
	 * @return Список врачей.
	 */
	@GetMapping("/doctors")
	@ResponseBody
	public List<Doctor> getDoctorsByType(@RequestParam("type") String type) {
		List<Doctor> doctors = doctorService.getDoctorsByType(type);
		return doctors;
	}
	
	/**
	 * Обрабатывает GET-запрос для получения списка доступных дат для записи на прием.
	 * 
	 * @param doctorId ИД врача.
	 * @return Список доступных дат.
	 */
	@GetMapping("/dates")
	@ResponseBody
	public List<LocalDate> getAvailableDates(@RequestParam("doctorId") String doctorId) {
		return appointmentService.getAvailableDates(Long.parseLong(doctorId));
	}
	
	/**
	 * Обрабатывает GET-запрос для получения списка доступных времен для записи на прием.
	 * 
	 * @param doctorId ИД врача.
	 * @param date Дата записи.
	 * @return Список доступного времени.
	 */
	@GetMapping("/times")
	@ResponseBody
	public List<LocalTime> getAvailableTimes(@RequestParam("doctorId") String doctorId,
											 @RequestParam("date") String date) {
		List<LocalTime> times = appointmentService.getAvailableTime(Long.parseLong(doctorId), LocalDate.parse(date));
		return times;
	}
	
	/**
	 * Обрабатывает POST-запрос для добавления записи на прием.
	 * 
	 * @param doctorId ИД врача.
	 * @param date Дата записи.
	 * @param time Время записи.
	 * @return Строка перенаправления пользователя.
	 */
	@PostMapping
	public String addAppointment(@RequestParam("doctorSelect") long doctorId,
									   @RequestParam("dateSelect") String date,
									   @RequestParam("timeSelect") String time) {
		appointmentService.makeAppointment(doctorId, LocalDate.parse(date), LocalTime.parse(time));		
		return "redirect:/";
	}
}
