package com.bagas.hospital_website.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_website.models.Appointment;

import jakarta.transaction.Transactional;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.bagas.hospital_website.models.User;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query(value = "SELECT * FROM appointments WHERE DATE(time) = :date LIMIT 1", nativeQuery = true)
	Optional<Appointment> findAppointmentByDate(@Param("date") LocalDate date);
	
	@Query(value = "SELECT DISTINCT DATE(time) FROM appointments WHERE doctor_id = :doctorId AND user_id IS NULL AND DATE(time) BETWEEN :startDate AND :endDate",
			nativeQuery = true)
	List<Date> findAvailableDatesByDoctorDates(@Param("doctorId") long doctorId,
									   		   @Param("startDate") LocalDate startDate,
									   		   @Param("endDate") LocalDate endDate);
	
	@Query(value = "SELECT cast(time as time) FROM appointments WHERE doctor_id = :doctorId AND user_id IS NULL AND DATE(time) = :date",
			nativeQuery = true)
	List<Time> findAvailableTimeByDoctorDate(@Param("doctorId") long doctorId,
											 @Param("date") LocalDate date);

	@Modifying
	@Transactional
	@Query(value = "UPDATE appointments set user_id = :userId where doctor_id = :doctorId and time = :timestamp", nativeQuery = true)
	void setUserToAppointmentByDoctorTimestamp(@Param("doctorId") long doctorId, 
											   @Param("timestamp") LocalDateTime timestamp,
											   @Param("userId") long userId);
	
	List<Appointment> findByUserOrderByTime(User user);
}
