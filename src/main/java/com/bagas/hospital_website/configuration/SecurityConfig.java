package com.bagas.hospital_website.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс SecurityConfig определяет конфигурацию безопасности для веб-приложения.
 * Использует Spring Security для настройки аутентификации и авторизации пользователей.
 * Также определяет настройки фильтрации HTTP-запросов и шифрования паролей.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Определяет объект AuthenticationManager для аутентификации пользователей.
	 * 
	 * @param configuration Конфигурация аутентификации.
	 * @return Объект AuthenticationManager.
	 * @throws Exception Возникает, если не удается создать объект AuthenticationManager.
	 */
	@Bean
	AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	/**
	 * Определяет цепь фильтров безопасности для обработки HTTP-запросов и настройки авторизации.
	 * 
	 * @param http Объект HttpSecurity для настройки фильтрации HTTP-запросов.
	 * @return Объект SecurityFilterChain.
	 * @throws Exception Возникает, если не удается настроить SecurityFilterChain.
	 */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http.csrf(csrf -> csrf.disable())
    		.formLogin(form -> form
    				.loginPage("/sign")
    				.loginProcessingUrl("/login")
    				.defaultSuccessUrl("/"	)
    				.permitAll())
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/appointment")
    				.authenticated()
     				.requestMatchers("/**")
    				.permitAll()
    				.anyRequest()
    				.authenticated());
      	
		return http.build();
	}

    /**
     * Определяет объект PasswordEncoder для шифрования паролей пользователей.
     * 
     * @return Объект PasswordEncoder.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
