package com.bagas.hospital_website.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_website.models.Appointment;


import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


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
}
