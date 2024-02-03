package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.ChildInstitution;
import com.bagas.hospital_website.repositories.ChildInstitutionRepository;

import jakarta.transaction.Transactional;

/**
 * Класс ChildInstitutionService отвечает за управление операциями, связанными с детскими учреждениями.
 * Он взаимодействует с ChildInstitutionRepository для доступа и управления данными детских учреждений
 * в базе данных. 
 */

@Service
public class ChildInstitutionService {

	/**
	 * Репозиторий ChildInstitutionRepository для доступа и управления детскими учреждениями в базе данных.
	 */
	@Autowired
	private ChildInstitutionRepository childInstitutionRepo;
	
	/**
	 * Возвращает список всех детских учреждений.
	 * 
	 * @return Список детских учреждений.
	 */
	public List<ChildInstitution> getAllChildInstitutions() {
		return childInstitutionRepo.findAll();
	}
	
	/**
	 * Удаляет детское учреждение по его идентификатору.
	 * 
	 * @param id ИД детского учреждения, которое необходимо удалить.
	 */
	@Transactional
	public void deleteChildInstitutionById(long id) {
		childInstitutionRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новое детское учреждение с указанным именем.
	 * 
	 * @param name Имя нового детского учреждения.
	 */
	@Transactional
	public void addChildInstitution(String name) {
		ChildInstitution childInstitution = new ChildInstitution();
		childInstitution.setName(name);
		
		childInstitutionRepo.save(childInstitution);
	}
}
