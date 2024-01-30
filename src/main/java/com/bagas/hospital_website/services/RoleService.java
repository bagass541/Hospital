package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Role;
import com.bagas.hospital_website.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}
	
	public Role getByAuthority(String authority) {
		return roleRepo.findByAuthority(authority);
	}
}

