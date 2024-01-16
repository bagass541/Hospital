package com.bagas.hospital_website.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		Optional<User> userOptional = userRepo.findByName(username);
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not exists by Username");
		}
		
		User user = userOptional.get();
		
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.authorities(authorities)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}
