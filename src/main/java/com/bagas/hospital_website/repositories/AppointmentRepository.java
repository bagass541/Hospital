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

/**
 * Репозиторий для доступа к сущностям Appointment.
 * Расширяет интерфейс JpaRepository<Appointment, Long>.
 */

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	/**
	 * Находит запись на пример по указанной дате.
	 * 
	 * @param date Дата приема
	 * @return Optional с найденной записью на прием или пустым значением, если запись не найдена.
	 */
	@Query(value = "SELECT * FROM appointments WHERE DATE(time) = :date LIMIT 1", nativeQuery = true)
	Optional<Appointment> findAppointmentByDate(@Param("date") LocalDate date);

	/**
	 * Находит доступные даты приема для указанного доктора, начиная с определенной даты.
	 * 
	 * @param doctorId ИД врача, по которому производится поиск.
	 * @param startDate Начальная дата.
	 * @return Список доступных дат на прием.
	 */
	@Query(value = "SELECT DISTINCT FUNCTION('DATE', a.time) as date FROM Appointment a WHERE a.doctor.id = :doctorId AND a.user.id IS NULL AND FUNCTION('DATE', a.time) >= :startDate ORDER BY date")
	List<Date> findAvailableDatesByDoctorDate(@Param("doctorId") long doctorId,
									   		   @Param("startDate") LocalDate startDate);
	
	/**
	 * Находит доступное время приема для указанного врача и даты.
	 * 
	 * @param doctorId ИД врача, по которому производится поиск.
	 * @param date Дата, по которой производится поиск.
	 * @return Список доступного времени для приема.
	 */
	@Query(value = "SELECT cast(time as time) AS t2 FROM appointments WHERE doctor_id = :doctorId AND user_id IS NULL AND DATE(time) = :date ORDER BY t2",
			nativeQuery = true)
	List<Time> findAvailableTimeByDoctorDate(@Param("doctorId") long doctorId,
											 @Param("date") LocalDate date);

	/**
	 * Устанавливает пользователя для записи на прием по указанному врачу, дате и времени.
	 * 
	 * @param doctorId ИД врача, к которому производится запись.
	 * @param timestamp Дата и время, на которое записались.
	 * @param userId ИД пользователя, который записался на прием.
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE Appointment a SET a.user.id = :userId WHERE a.doctor.id = :doctorId and a.user.id IS NULL and a.time = :timestamp")
	void setUserToAppointmentByDoctorTimestamp(@Param("doctorId") long doctorId, 
											   @Param("timestamp") LocalDateTime timestamp,
											   @Param("userId") long userId);
	
	/**
	 * Находит все записи на прием для указанного пользователя, сортированние по дате и времени.
	 * 
	 * @param user Пользователь, по которому производится поиск.
	 * @param date Сегодняшняя дата
	 * @return Список записей на прием для указанного пользователя.
	 */
	@Query(value = "SELECT a FROM Appointment a WHERE FUNCTION('DATE', a.time) > :date AND a.user.id = :userId ORDER BY a.time")
	List<Appointment> findByUserOrderByTime(@Param("userId") long userId, 
											@Param("date") LocalDate date);
	
	/**
	 * Находит все записи на прием для указанного доктора на указанную дату.
	 * 
	 * @param doctorId ИД врача, по которому производится поиск.
	 * @param date Дата приема.
	 * @return Список записей на прием для указанного доктора и даты.
	 */
	@Query(value = "SELECT a FROM Appointment a LEFT JOIN FETCH a.user au LEFT JOIN FETCH au.userInfo auu WHERE a.doctor.id = :doctorId AND FUNCTION('DATE', a.time) = :date")
	List<Appointment> findByDoctorDate(@Param("doctorId") long doctorId, 
									   @Param("date") LocalDate date);
}
