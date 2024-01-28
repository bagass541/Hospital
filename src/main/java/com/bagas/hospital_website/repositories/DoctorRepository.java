package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;

import java.util.List;

	
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	List<Doctor> findByDoctorType(DoctorType doctorType);
	
	@Query(value = "SELECT d FROM Doctor d ORDER BY d.doctorType, d.fio")
	List<Doctor> findAllOrderByDoctorTypeFio();

}
