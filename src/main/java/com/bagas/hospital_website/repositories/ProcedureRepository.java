package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Procedure;

/**
 * Репозиторий для доступа к сущностям Procedure.
 * Расширяет интерфейс JpaRepository<Procedure, Long>.
 */

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

}
