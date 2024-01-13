package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Stomatology;
import com.bagas.hospital_website.repositories.StomatologyRepository;

@Service
public class StomatologyService {

	@Autowired
	private StomatologyRepository stomatologyRepo;
	
	public List<Stomatology> getAllStomatologies() {
		return stomatologyRepo.findAll();
	}
}
