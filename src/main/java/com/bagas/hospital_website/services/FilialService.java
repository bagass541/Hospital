package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.Filial;
import com.bagas.hospital_website.repositories.FilialRepository;

import jakarta.transaction.Transactional;

@Service
public class FilialService {
	
	@Autowired
	private FilialRepository filialRepo;
	
	public List<Filial> getAllFilials() {
		return filialRepo.findAll();
	}
	
	@Transactional
	public void deleteFilialById(long id) {
		filialRepo.deleteById(id);
	}
	
	@Transactional
	public void addFilial(String name) {
		Filial filial = new Filial();
		filial.setName(name);
		
		filialRepo.save(filial);
	}
}
