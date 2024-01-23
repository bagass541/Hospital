package com.bagas.hospital_website.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
}
