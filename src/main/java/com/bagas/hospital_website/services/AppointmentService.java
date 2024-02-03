package com.bagas.hospital_website.services;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Appointment;
import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.User;
import com.bagas.hospital_website.repositories.AppointmentRepository;
import com.bagas.hospital_website.util.TimeIntervalGenerator;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

/**
 * Класс AppointmentService отвечает за управление и обработку операций, связанных с записями на прием
 * в системе здравоохранения. Он взаимодействует с AppointmentRepository, DoctorService, UserService и
 * TimeIntervalGenerator для выполнения различных задач, таких как создание записей, их получение и удаление.
 */

@Service
public class AppointmentService {

	/**
	 * Репозиторий AppointmentRepository для доступа и управления записями в базе данных.
	 */
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	/**
	 * Сервис DoctorService для получения информации о врачах.
	 */
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * Сервис UserService для управления операциями, связанными с пользователями.
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * TimeIntervalGenerator для генерации временных интервалов для записей на прием.
	 */
	@Autowired
	private TimeIntervalGenerator timeIntervalGenerator;
	
	/**
	 * Генерирует записи на прием для следующего буднего дня, используя запланированную задачу, каждый день в 00:00.
	 */
	@Scheduled(cron = "0 1 0 * * *")
	public void generateAppointmentsForNextWeekday() {
		LocalDate date = adjustWeekendToWeekday(LocalDate.now().plusDays(7));
		List<Doctor> doctors = doctorService.getAllDoctors();
		
		generateAppointmentsForDate(date, doctors);
	}
	
	
	/**
	 * Генерирует записи на прием на всю неделю при запуске приложения без записей о приемах.
	 */
	@PostConstruct
	public void generateAppointmentsForWeek() {
		LocalDate date = adjustWeekendToWeekday(LocalDate.now().plusDays(1));
		
		
		Optional<Appointment> appointment = appointmentRepo.findAppointmentByDate(date);
		if(appointment.isEmpty()) {
			List<Doctor> doctors = doctorService.getAllDoctors();
			for(int i = 0; i < 7; i++) {
				generateAppointmentsForDate(date, doctors);
				date = adjustWeekendToWeekday(date.plusDays(1));
			}
		}
	}
	
	/**
	 * Генерирует записи на прием для конкретного врача на всю неделю.
	 * 
	 * @param idDoctor Идентификатор врача, для которого нужно сгенерировать записи.
	 */
	public void generateAppointmentsForWeek(long idDoctor) {
		LocalDate date = adjustWeekendToWeekday(LocalDate.now().plusDays(1));
		Doctor doctor = doctorService.getDoctorById(idDoctor);

		for(int i = 0; i < 7; i++) {
			generateAppointmentsForDate(date, doctor);
			date = adjustWeekendToWeekday(date.plusDays(1));
		}	
	}
	
	/**
	 * Генерирует записи на прием для указанной даты и списка врачей.
	 * 
	 * @param date Дата, для которой генерируются записи на прием.
	 * @param doctors Список врачей, для которых создаются записи.
	 */
	@Transactional
	public void generateAppointmentsForDate(LocalDate date, List<Doctor> doctors) {
		List<Appointment> appointments = new LinkedList<>();
		
		for(Doctor doctor : doctors) {
			List<LocalTime> intervals = timeIntervalGenerator.generateIntervals(doctor.getStartWork(), doctor.getEndWork());
			for(LocalTime interval : intervals) {
				Appointment appointment = createAppointment(date.atTime(interval), doctor);					
				appointments.add(appointment);
			}
		}	
		appointmentRepo.saveAll(appointments);
	}
	
	/**
	 * Генерирует записи на прием для указанной даты и конкретного врача.
	 * 
	 * @param date Дата, для которой генерируются записи на прием.
	 * @param doctor Врач, для которого создаются записи.
	 */
	@Transactional
	public void generateAppointmentsForDate(LocalDate date, Doctor doctor) {
		List<Appointment> appointments = new LinkedList<>();
		
		List<LocalTime> intervals = timeIntervalGenerator.generateIntervals(doctor.getStartWork(), doctor.getEndWork());
		for(LocalTime interval : intervals) {
			Appointment appointment = createAppointment(date.atTime(interval), doctor);		
			appointments.add(appointment);
		}		
		appointmentRepo.saveAll(appointments);
	}
	
	/**
	 * Корректирует дату, перемещая ее на ближайший будний день, если она выпадает на выходной день.
	 * 
	 * @param date Исходная дата.
	 * @return Скорректированная дата.
	 */
	private LocalDate adjustWeekendToWeekday(LocalDate date) {
		if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.getDayOfWeek() == DayOfWeek.SATURDAY ? date.plusDays(2) : date.plusDays(1);
		} 
		
		return date;
	}
	
	/**
	 * Создает объект записи с указанной датой, временем и доктором.
	 * 
	 * @param time Дата и время записи на прием.
	 * @param doctor Врач, для которого создается запись.
	 * @return Объект записи на прием.
	 */
	private Appointment createAppointment(LocalDateTime time, Doctor doctor) {
		Appointment appointment = new Appointment();
		appointment.setTime(time);
		appointment.setDoctor(doctor);
		
		return appointment;
	}
	
	/**
	 * Получается список доступных дат для записи у указанного врача.
	 * 
	 * @param doctorId ИД врача.
	 * @return Список доступных дат у врача.
	 */
	public List<LocalDate> getAvailableDates(long doctorId) {
		LocalDate startDate = LocalDate.now().plusDays(1);
		return appointmentRepo.findAvailableDatesByDoctorDate(doctorId, startDate).stream()
				.map(Date::toLocalDate)
				.collect(Collectors.toList());
	}
	
	/**
	 * Получает список доступного времени для записи на прием у указанного врача.
	 * 
	 * @param doctorId ИД врача.
	 * @param date Дата, для которой ищется доступное время.
	 * @return Список доступного времени для записи на прием.
	 */
	public List<LocalTime> getAvailableTime(long doctorId, LocalDate date) {
		return appointmentRepo.findAvailableTimeByDoctorDate(doctorId, date).stream()
				.map(Time::toLocalTime)
				.map(time -> time.truncatedTo(ChronoUnit.MINUTES))
				.collect(Collectors.toList());
	}
	
	/**
	 * Записывает пользователя на прием к указанному врачу в указанную дату и время.
	 * 
	 * @param doctorId ИД врача.
	 * @param date Дата приема.
	 * @param time Время приема.
	 */
	public synchronized void makeAppointment(long doctorId, LocalDate date, LocalTime time) {
		LocalDateTime timestamp = date.atTime(time);	
		User user = userService.getCurrentUser();
		appointmentRepo.setUserToAppointmentByDoctorTimestamp(doctorId, timestamp, user.getId());
	}
	
	/**
	 * Получает  список доступных записей на прием, сделанных текущим пользователем, отсортированных по времени.
	 * 
	 * @return Список записей на прием пользователя.
	 */
	public List<Appointment> getAllAppointmentsByUser() {
		User user = userService.getCurrentUser();
		List<Appointment> appointments =  appointmentRepo.findByUserOrderByTime(user);
		
		return appointments;
	}
	
	/**
	 * Получает список записей на прием для указанного врача и даты.
	 * 
	 * @param doctorId ИД врача.
	 * @param date Дата приема.
	 * @return Список записей на прием врача в указанную дату.
	 */
	public List<Appointment> getAppointmentsByDoctorDate(long doctorId, LocalDate date) {
		return appointmentRepo.findByDoctorDate(doctorId, date);
	}
		
	/**
	 * Удаляет запись по ее ИД.
	 * 
	 * @param id ИД записи на прием, которую необходимо удалить.
	 */
	public void deleteAppointmentById(long id) {
		appointmentRepo.deleteById(id);

	}
}
