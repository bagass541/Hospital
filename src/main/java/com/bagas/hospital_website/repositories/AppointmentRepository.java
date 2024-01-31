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

	@Query(value = "SELECT DISTINCT FUNCTION('DATE', a.time) FROM Appointment a WHERE a.doctor.id = :doctorId AND a.user.id IS NULL AND FUNCTION('DATE', a.time) >= :startDate")
	List<Date> findAvailableDatesByDoctorDates(@Param("doctorId") long doctorId,
									   		   @Param("startDate") LocalDate startDate);
	
	@Query(value = "SELECT cast(time as time) FROM appointments WHERE doctor_id = :doctorId AND user_id IS NULL AND DATE(time) = :date",
			nativeQuery = true)
	List<Time> findAvailableTimeByDoctorDate(@Param("doctorId") long doctorId,
											 @Param("date") LocalDate date);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Appointment a SET a.user.id = :userId where a.doctor.id = :doctorId and a.user.id IS NULL and a.time = :timestamp")
	void setUserToAppointmentByDoctorTimestamp(@Param("doctorId") long doctorId, 
											   @Param("timestamp") LocalDateTime timestamp,
											   @Param("userId") long userId);
	
	List<Appointment> findByUserOrderByTime(User user);
	
	@Query(value = "SELECT a FROM Appointment a JOIN a.user au JOIN au.userInfo auu where a.doctor.id = :doctorId AND FUNCTION('DATE', a.time) = :date")
	List<Appointment> findByDoctorDate(@Param("doctorId") long doctorId, 
									   @Param("date") LocalDate date);
}
