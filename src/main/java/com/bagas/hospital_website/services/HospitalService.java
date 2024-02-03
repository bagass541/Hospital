package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Hospital;
import com.bagas.hospital_website.repositories.HospitalRepository;

import jakarta.transaction.Transactional;

/**
 * Класс HospitalService отвечает за управление операциями, связанными с больницами в системе.
 * Взаимодействует с HospitalRepository для доступа и управления данными больниц в базе данных.
 */

@Service
public class HospitalService {

	/**
	 * Репозиторий HospitalRepository для доступа и управления данными больниц в базе данных.
	 */
	@Autowired
	private HospitalRepository hospitalRepo;
	
	/**
	 * Возвращает список всех больниц.
	 * 
	 * @return Список больниц.
	 */
	public List<Hospital> getAllHospitals() {
		return hospitalRepo.findAll();
	}
	
	/**
	 * Удаляет больницу по её ИД.
	 * 
	 * @param id ИД больницы, которую необходимо удалить.
	 */
	@Transactional
	public void deleteHospitalById(long id) {
		hospitalRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новую больницу с указанным именем.
	 * 
	 * @param name Имя новой больницы.
	 */
	@Transactional
	public void addHospital(String name) {
		Hospital hospital = new Hospital();
		hospital.setName(name);
		
		hospitalRepo.save(hospital);
	}
}
