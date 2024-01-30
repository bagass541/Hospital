package com.bagas.hospital_website.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bagas.hospital_website.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u JOIN u.authorities a JOIN u.userInfo ui where a.authority = 'ROLE_USER' ORDER BY ui.fio")
	List<User> findAllUsersOrderByFio();
	
}
