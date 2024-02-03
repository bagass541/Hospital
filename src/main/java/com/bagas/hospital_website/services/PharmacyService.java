package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Pharmacy;
import com.bagas.hospital_website.repositories.PharmacyRepository;

import jakarta.transaction.Transactional;

/**
 * Класс PharmacyService отвечает за управление операциями, связанными с аптеками в системе.
 * Взаимодействует с PharmacyRepository для доступа и управления данными аптек в базе данных.
 */

@Service
public class PharmacyService {
	
	/**
	 * Репозиторий PharmacyRepository для доступа и управления данными аптек в базе данных.
	 */
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	/**
	 * Возвращает список всех аптек.
	 * 
	 * @return Список аптек.
	 */
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepo.findAll();
	}
	
	/**
	 * Удаляет аптеку по её ИД.
	 * 
	 * @param id ИД аптеки, которую необходимо удалить.
	 */
	@Transactional
	public void deletePharmacyById(long id) {
		pharmacyRepo.deleteById(id);
	}
	
	/**
	 * Добавляет новую аптеку с указанным именем.
	 * 
	 * @param name Имя новой поликлиники.
	 */
	@Transactional
	public void addPharmacy(String name) {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setName(name);
		
		pharmacyRepo.save(pharmacy);
	}
}
