package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Hospital;
import com.bagas.hospital_website.repositories.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepo;
	
	public List<Hospital> getAllHospitals() {
		return hospitalRepo.findAll();
	}
	
	public void deleteHospitalById(long id) {
		hospitalRepo.deleteById(id);
	}
}
