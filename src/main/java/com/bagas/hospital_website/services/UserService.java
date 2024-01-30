package com.bagas.hospital_website.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Role;
import com.bagas.hospital_website.models.User;
import com.bagas.hospital_website.models.UserInfo;
import com.bagas.hospital_website.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByUsername(username);
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not exists by Username");
		}
		
		User user = userOptional.get();
		
		return user;
	}
	
	public List<User> getAllUsersOrderByFio() {
		return userRepo.findAllUsersOrderByFio();
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
	
	@Transactional
	public void deleteUserById(long id) {
		userRepo.deleteById(id);
	}
	
	@Transactional
	public void addUser(String username, String password, String fio, String number, String authority) {			
		User user = createUser(username, password, fio, number, authority);
		userRepo.save(user);
		
	}
	
	private User createUser(String username, String password, String fio, String number, String authority) {
		UserInfo userInfo = new UserInfo();
		userInfo.setFio(fio);
		userInfo.setNumber(number);
		
		Role role = roleService.getByAuthority(authority);
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		
		User user = User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.userInfo(userInfo)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.isEnabled(true)
				.authorities(roles)
				.build();
	
		return user;
	}
}
