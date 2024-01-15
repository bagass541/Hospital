package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bagas.hospital_website.models.ChildInstitution;

public interface ChildInstitutionRepository extends JpaRepository<ChildInstitution, Long> {

}
