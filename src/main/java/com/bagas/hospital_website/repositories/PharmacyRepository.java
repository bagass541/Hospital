package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
