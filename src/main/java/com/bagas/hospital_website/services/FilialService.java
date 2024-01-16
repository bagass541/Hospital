package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Filial;
import com.bagas.hospital_website.repositories.FilialRepository;

@Service
public class FilialService {
	
	@Autowired
	private FilialRepository filialRepo;
	
	public List<Filial> getAllFilials() {
		return filialRepo.findAll();
	}
}
