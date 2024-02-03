package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.structure.Hospital;

/**
 * Репозиторий для доступа к сущностям Hospital.
 * Расширяет интерфейс JpaRepository<Hospital, Long>.
 */

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
