package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Polyclinic;
import com.bagas.hospital_website.repositories.PolyclinicRepository;

import jakarta.transaction.Transactional;

/**
 * Класс PolyclinicService отвечает за управление операциями, связанными с поликлиниками в системе.
 * Взаимодействует с PolyclinicRepository для доступа и управления данными поликлиник в базе данных.
 */

@Service
public class PolyclinicService {
	
	/**
	 * Репозиторий PolyclinicRepository для доступа и управления данными поликлиник в базе данных.
	 */
	@Autowired
	private PolyclinicRepository polyclinicRepo;
	
	/**
	 * Возвращает список всех поликлиник.
	 * 
	 * @return Список поликлиник.
	 */
	public List<Polyclinic> getAllPolyclinics() {
		return polyclinicRepo.findAll();
	}
	
	/**
	 * Удаляет поликлинику по её ИД.
	 * 
	 * @param id ИД поликлиники, которую необходимо удалить.
	 */
	@Transactional
	public void deletePolyclinicById(long id) {
		polyclinicRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новую поликлинику с указанным именем.
	 * 
	 * @param name Имя новой поликлиники.
	 */
	@Transactional
	public void addPolyclinic(String name) {
		Polyclinic polyclinic = new Polyclinic();
		polyclinic.setName(name);
		
		polyclinicRepo.save(polyclinic);
	}
}
