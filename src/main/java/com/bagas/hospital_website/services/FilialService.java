package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Filial;
import com.bagas.hospital_website.repositories.FilialRepository;

import jakarta.transaction.Transactional;

/**
 * Класс FilialService отвечает за управление операциями, связанными с филиалами в системе.
 * Взаимодействует с FilialRepository для доступа и управления данными филиалов в базе данных.
 */

@Service
public class FilialService {
	
	/**
	 * Репозиторий FilialRepository для доступа и управления данными филиалов в базе данных.
	 */
	@Autowired
	private FilialRepository filialRepo;
	
	/**
	 * Возвращает список всех филиалов.
	 * 
	 * @return Список филиалов.
	 */
	public List<Filial> getAllFilials() {
		return filialRepo.findAll();
	}
	
	/**
	 * Удаляет филиал по его ИД.
	 * 
	 * @param id ИД филиала, который необходимо удалить.
	 */
	@Transactional
	public void deleteFilialById(long id) {
		filialRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новый филиал с указанным именем.
	 * 
	 * @param name Имя нового филиала.
	 */
	@Transactional
	public void addFilial(String name) {
		Filial filial = new Filial();
		filial.setName(name);
		
		filialRepo.save(filial);
	}
}
