package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByAuthority(String authority);
}
