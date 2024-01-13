package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
