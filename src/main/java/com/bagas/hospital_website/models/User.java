package com.bagas.hospital_website.models;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserInfo userInfo;
	
	private String password;
	
	@Column(name  = "is_account_non_expired")
	boolean isAccountNonExpired;
	
	@Column(name = "is_account_non_locked")
	boolean isAccountNonLocked;
	
	@Column(name = "is_credentials_non_expired")
	boolean isCredentialsNonExpired;
	
	@Column(name = "is_enabled")
	boolean isEnabled;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Role> authorities;

}
