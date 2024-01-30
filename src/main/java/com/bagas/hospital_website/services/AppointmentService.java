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

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeIntervalGenerator timeIntervalGenerator;
	
	@Scheduled(cron = "0 1 0 * * *")
	public void generateAppointmentsForNextWeekday() {
		LocalDate date = adjustWeekendToWeekday(LocalDate.now().plusDays(7));
		List<Doctor> doctors = doctorService.getAllDoctors();
		
		generateAppointmentsForDate(date, doctors);
	}
	
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
	
	public void generateAppointmentsForWeek(long idDoctor) {
		LocalDate date = adjustWeekendToWeekday(LocalDate.now().plusDays(1));
		Doctor doctor = doctorService.getDoctorById(idDoctor);

		for(int i = 0; i < 7; i++) {
			generateAppointmentsForDate(date, doctor);
			date = adjustWeekendToWeekday(date.plusDays(1));
		}	
	}
	
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
	
	private LocalDate adjustWeekendToWeekday(LocalDate date) {
		if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.getDayOfWeek() == DayOfWeek.SATURDAY ? date.plusDays(2) : date.plusDays(1);
		} 
		
		return date;
	}
	
	private Appointment createAppointment(LocalDateTime time, Doctor doctor) {
		Appointment appointment = new Appointment();
		appointment.setTime(time);
		appointment.setDoctor(doctor);
		
		return appointment;
	}
	
	public List<LocalDate> getAvailableDates(long doctorId) {
		LocalDate startDate = LocalDate.now().plusDays(1);
		return appointmentRepo.findAvailableDatesByDoctorDates(doctorId, startDate).stream()
				.map(Date::toLocalDate)
				.collect(Collectors.toList());
	}
	
	public List<LocalTime> getAvailableTime(long doctorId, LocalDate date) {
		return appointmentRepo.findAvailableTimeByDoctorDate(doctorId, date).stream()
				.map(Time::toLocalTime)
				.map(time -> time.truncatedTo(ChronoUnit.MINUTES))
				.collect(Collectors.toList());
	}
	
	public synchronized void makeAppointment(long doctorId, LocalDate date, LocalTime time) {
		LocalDateTime timestamp = date.atTime(time);	
		User user = userService.getCurrentUser();
		appointmentRepo.setUserToAppointmentByDoctorTimestamp(doctorId, timestamp, user.getId());
	}
	
	
	public List<Appointment> getAllAppointmentsByUser() {
		User user = userService.getCurrentUser();
		List<Appointment> appointments =  appointmentRepo.findByUserOrderByTime(user);
		
		return appointments;
	}
	
	public void deleteAppointmentById(long id) {
		appointmentRepo.deleteById(id);
	}
}
