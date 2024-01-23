package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.structure.ChildInstitution;
import com.bagas.hospital_website.repositories.ChildInstitutionRepository;

@Service
public class ChildInstitutionService {

	@Autowired
	private ChildInstitutionRepository childInstitutionRepo;
	
	public List<ChildInstitution> getAllChildInstitutions() {
		return childInstitutionRepo.findAll();
	}
}
