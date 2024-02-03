package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Stomatology;
import com.bagas.hospital_website.repositories.StomatologyRepository;

import jakarta.transaction.Transactional;

/**
 * Класс StomatologyService отвечает за управление операциями, связанными с стоматологическими клиниками в системе.
 * Взаимодействует с StomatologyRepository для доступа и управления данными стоматологических клиник в базе данных.
 */

@Service
public class StomatologyService {

	/**
	 * Репозиторий StomatologyRepository для доступа и управления данными стоматологических клиник в базе данных.
	 */
	@Autowired
	private StomatologyRepository stomatologyRepo;
	
	/**
	 * Возвращает список всех стоматологических клиник.
	 * 
	 * @return Список стоматологических клиник.
	 */
	public List<Stomatology> getAllStomatologies() {
		return stomatologyRepo.findAll();
	}
	
	/**
	 * Удаляет стоматологическую клинику по её ИД.
	 * 
	 * @param id ИД стоматологической клиники, которую необходимо удалить.
	 */
	@Transactional
	public void deleteStomatologyById(long id) {
		stomatologyRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новую стоматологическую клинику с указанным именем.
	 * 
	 * @param name Имя новой стоматологической клиники.
	 */
	@Transactional
	public void addStomatology(String name) {
		Stomatology stomatology = new Stomatology();		
		stomatology.setName(name);
		
		stomatologyRepo.save(stomatology);
	}
}
