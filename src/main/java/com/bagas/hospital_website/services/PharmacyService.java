package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Pharmacy;
import com.bagas.hospital_website.repositories.PharmacyRepository;

import jakarta.transaction.Transactional;

@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepo.findAll();
	}
	
	@Transactional
	public void deletePharmacyById(long id) {
		pharmacyRepo.deleteById(id);
	}
	
	@Transactional
	public void addPharmacy(String name) {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setName(name);
		
		pharmacyRepo.save(pharmacy);
	}
}
