package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.structure.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
