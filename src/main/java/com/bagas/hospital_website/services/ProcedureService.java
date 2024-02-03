package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Procedure;
import com.bagas.hospital_website.repositories.ProcedureRepository;

import jakarta.transaction.Transactional;

/**
 * Класс ProcedureService отвечает за управление операциями, связанными с медицинскими процедурами в системе.
 * Взаимодействует с ProcedureRepository для доступа и управления данными медицинских процедур в базе данных.
 */

@Service
public class ProcedureService {
	
	/**
	 * Репозиторий ProcedureRepository для доступа и управления данными медицинских процедур в базе данных.
	 */
	@Autowired
	private ProcedureRepository procedureRepo;
	
	/**
	 * Возвращает список всех медицинских процедур.
	 * 
	 * @return Список медицинских процедур.
	 */
	public List<Procedure> getAllServices() {
		return procedureRepo.findAll();
	}
	
	/**
	 * Удаляет медицинскую процедуру по её ИД.
	 * 
	 * @param id ИД медицинской процедуры, которую необходимо удалить.
	 */
	@Transactional
	public void deleteProcedure(long id) {
		procedureRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новую медицинскую процедуру с указанными параметрами.
	 * 
	 * @param name Название медицинской процедуры.
	 * @param minutes Продолжительность медицинской процедуры в минутах.
	 * @param price Стоимость медицинской процедуры.
	 */
	@Transactional
	public void addProcedure(String name, int minutes, int price) {
		Procedure procedure = new Procedure();
		procedure.setName(name);
		procedure.setMinutes(minutes);
		procedure.setPrice(price);
		
		procedureRepo.save(procedure);
	}
}
