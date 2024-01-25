package com.bagas.hospital_website.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.User;
import com.bagas.hospital_website.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByUsername(username);
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not exists by Username");
		}
		
		User user = userOptional.get();
		
		return user;
	}
	
	public String getFio() {
		User user = getCurrentUser();		
		return user.getUserInfo().getFio();
	}
	
	public String getNumber() {
		User user = getCurrentUser();
		return user.getUserInfo().getNumber();
	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}
}
