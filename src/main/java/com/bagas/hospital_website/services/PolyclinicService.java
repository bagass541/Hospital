package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Polyclinic;
import com.bagas.hospital_website.repositories.PolyclinicRepository;

@Service
public class PolyclinicService {

	@Autowired
	private PolyclinicRepository polyclinicRepo;
	
	public List<Polyclinic> getAllPolyclinics() {
		return polyclinicRepo.findAll();
	}
	
	public void deletePolyclinicById(long id) {
		polyclinicRepo.deleteById(id);
	}
}
