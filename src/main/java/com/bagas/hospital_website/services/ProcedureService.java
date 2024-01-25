package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Procedure;
import com.bagas.hospital_website.repositories.ProcedureRepository;

@Service
public class ProcedureService {
	
	@Autowired
	private ProcedureRepository procedureRepo;
	
	public List<Procedure> getAllServices() {
		return procedureRepo.findAll();
	}
	
	public void deleteProcedure(long id) {
		procedureRepo.deleteById(id);
	}
	
	public void addProcedure(String name, int minutes, int price) {
		Procedure procedure = new Procedure();
		procedure.setName(name);
		procedure.setMinutes(minutes);
		procedure.setPrice(price);
		
		procedureRepo.save(procedure);
	}
}
