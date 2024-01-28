package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;
import com.bagas.hospital_website.models.DoctorTypeConverter;
import com.bagas.hospital_website.repositories.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private DoctorTypeConverter doctorTypeConverter;
	
	public List<Doctor> getAllDoctors() {
		return doctorRepo.findAll();
	}
	
	public List<Doctor> getAllDoctorsOrderByDoctorTypeFio() {
		return doctorRepo.findAllOrderByDoctorTypeFio();
	}
	
	public List<Doctor> getDoctorsByType(String type) {
		DoctorType doctorType = doctorTypeConverter.convertToEntityAttribute(type);
		return doctorRepo.findByDoctorType(doctorType);
	}
	
	public void deleteDoctorById(long id) {
		doctorRepo.deleteById(id);
	}
}
