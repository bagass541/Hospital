package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.structure.Stomatology;

/**
 * Репозиторий для доступа к сущностям Stomatology.
 * Расширяет интерфейс JpaRepository<Stomatology, Long>.
 */

public interface StomatologyRepository extends JpaRepository<Stomatology, Long>{

}
