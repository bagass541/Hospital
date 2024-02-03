package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.structure.Polyclinic;

/**
 * Репозиторий для доступа к сущностям Polyclinic.
 * Расширяет интерфейс JpaRepository<Polyclinic, Long>.
 */

public interface PolyclinicRepository extends JpaRepository<Polyclinic, Long> {

}
