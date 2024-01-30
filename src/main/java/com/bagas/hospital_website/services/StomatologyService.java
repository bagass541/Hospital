package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Stomatology;
import com.bagas.hospital_website.repositories.StomatologyRepository;

import jakarta.transaction.Transactional;

@Service
public class StomatologyService {

	@Autowired
	private StomatologyRepository stomatologyRepo;
	
	public List<Stomatology> getAllStomatologies() {
		return stomatologyRepo.findAll();
	}
	
	@Transactional
	public void deleteStomatologyById(long id) {
		stomatologyRepo.deleteById(id);
	}
	
	@Transactional
	public void addStomatology(String name) {
		Stomatology stomatology = new Stomatology();		
		stomatology.setName(name);
		
		stomatologyRepo.save(stomatology);
	}
}
