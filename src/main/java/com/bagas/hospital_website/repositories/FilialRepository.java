package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.structure.Filial;

/**
 * Репозиторий для доступа к сущностям Filial.
 * Расширяет интерфейс JpaRepository<Filial, Long>.
 */

public interface FilialRepository extends JpaRepository<Filial, Long> {

}
