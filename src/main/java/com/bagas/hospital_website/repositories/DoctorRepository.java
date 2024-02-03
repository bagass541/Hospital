package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bagas.hospital_website.models.Doctor;
import com.bagas.hospital_website.models.DoctorType;

import java.util.List;

/**
 * Репозиторий для доступа к сущностям Doctor.
 * Расширяет интерфейс JpaRepository<Doctor, Long>.	
 */

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	/**
	 * Находит всех докторов с указанным типом.
	 * 
	 * @param doctorType Тип доктора.
	 * @return Список докторов с указанным типом.
	 */
	List<Doctor> findByDoctorType(DoctorType doctorType);
	
	/**
	 * Находит всех докторов с сортировкой по типу доктора и ФИО.
	 * 
	 * @return Список докторов, отсортироваанный по типу доктора и ФИО.
	 */
	@Query(value = "SELECT d FROM Doctor d ORDER BY d.doctorType, d.fio")
	List<Doctor> findAllOrderByDoctorTypeFio();

}
