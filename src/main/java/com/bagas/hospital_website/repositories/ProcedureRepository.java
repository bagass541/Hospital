package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

}