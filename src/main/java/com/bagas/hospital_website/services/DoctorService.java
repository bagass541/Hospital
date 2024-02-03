package com.bagas.hospital_website.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.models.DoctorTypeConverter;
import com.bagas.hospital_website.repositories.DoctorRepository;

import jakarta.transaction.Transactional;

/**
 * Класс DoctorService отвечает за управление операциями, связанными с врачами в медицинской системе.
 * Взаимодействует с DoctorRepository для доступа и управления данными врачей в базе данных, а также
 * использует DoctorTypeConverter для преобразования строкового представления типа врача в соответствующий
 * объект DoctorType и обратно. 
 */

@Service
public class DoctorService {

	/**
	 * Репозиторий DoctorRepository для доступа и управления данными врачей в базе данных.
	 */
	@Autowired
	private DoctorRepository doctorRepo;
	
	/*
	 * Конвертер DoctorTypeConverter для преобразования строкового представления типа врача.
	 */
	@Autowired
	private DoctorTypeConverter doctorTypeConverter;
	
	/**
	 * Возвращает список всех врачей.
	 * 
	 * @return Список врачей.
	 */
	public List<Doctor> getAllDoctors() {
		return doctorRepo.findAll();
	}
	
	/**
	 * Получает врача по его ИД.
	 * 
	 * @param id Идентификатор врача.
	 * @return Объект врача.
	 */
	public Doctor getDoctorById(long id) {
		return doctorRepo.findById(id).get();
	}
	
	/**
	 * Возвращает список всех врачей, отсортированных по типу врача и ФИО.
	 * 
	 * @return Список отсортированных врачей.
	 */
	public List<Doctor> getAllDoctorsOrderByDoctorTypeFio() {
		return doctorRepo.findAllOrderByDoctorTypeFio();
	}
	
	/**
	 * Получает список врачей по указанному типу.
	 * 
	 * @param type Строковое представление типа врача.
	 * @return Список врачей указанного типа.
	 */
	public List<Doctor> getDoctorsByType(String type) {
		DoctorType doctorType = doctorTypeConverter.convertToEntityAttribute(type);
		return doctorRepo.findByDoctorType(doctorType);
	}
	
	/**
	 * Удаляет врача по его ИД.
	 * 
	 * @param id ИД врача, которого необходимо удалить.
	 */
	@Transactional
	public void deleteDoctorById(long id) {
		doctorRepo.deleteById(id);
	}
	
	/**
	 * Добавляет нового врача с указанными параметрами.
	 * 
	 * @param doctorTypeStr Строковое представление типа врача.
	 * @param fio ФИО врача.
	 * @param startWork Время начала рабочего дня врача.
	 * @param endWork Время окончания рабочего дня врача.
	 * @return ИД нового врача.
	 */
	@Transactional
	public long addDoctor(String doctorTypeStr, String fio, LocalTime startWork, LocalTime endWork) {
		DoctorType doctorType = doctorTypeConverter.convertToEntityAttribute(doctorTypeStr);
		
		Doctor doctor = new Doctor();
		doctor.setDoctorType(doctorType);
		doctor.setFio(fio);
		doctor.setStartWork(startWork);
		doctor.setEndWork(endWork);
		
		 return doctorRepo.save(doctor).getId();	
	}
}
