package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Pharmacy;
import com.bagas.hospital_website.repositories.PharmacyRepository;

@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepo.findAll();
	}
}
