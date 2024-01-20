package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;

import java.util.List;

	
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	List<Doctor> findByDoctorType(@Param("type") DoctorType doctorType);

}
