package com.bagas.hospital_website.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("root")
				.password(passwordEncoder().encode("root"))
				.build();
		return new InMemoryUserDetailsManager(user1);
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http.csrf(csrf -> csrf.disable())
    		.formLogin(form -> form
    				.loginPage("/sign")
    				.loginProcessingUrl("/login")
    				.defaultSuccessUrl("/")
    				.permitAll())
    		.authorizeHttpRequests(auth -> auth
     				.requestMatchers("/**")
    				.permitAll()
    				.anyRequest()
    				.authenticated());
      	
		return http.build();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
