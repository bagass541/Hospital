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

/**
 * Класс UserService предоставляет функциональность для управления пользователями в системе.
 * Реализует интерфейс UserDetailsService, необходимый для аутентификации и авторизации пользователей.
 * Взаимодействует с UserRepository, RoleService и PasswordEncoder для доступа и управления данными пользователей в базе данных.
 */

@Service
public class UserService implements UserDetailsService {

	/**
	 * Репозиторий UserRepository для доступа и управления данными пользователей в базе данных.
	 */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Сервис RoleService для получения информации о ролях пользователей.
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * Объект для хеширования паролей.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Загружает пользователя по его логину.
	 * 
	 * @param username Логин пользователя.
	 * @return UserDetails объект пользователя.
	 * @throws UsernameNotFoundException Если пользователь с указанным именем не найден.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByUsername(username);
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException("User not exists by Username");
		}
		
		User user = userOptional.get();
		
		return user;
	}
	
	/**
	 * Возвращает список всех пользователей, отсортированных по ФИО.
	 * 
	 * @return Список пользователей.
	 */
	public List<User> getAllUsersOrderByFio() {
		return userRepo.findAllUsersOrderByFio();
	}
	
	/**
	 * Возвращает ФИО текущего пользователя.
	 * 
	 * @return ФИО текущего пользователя.
	 */
	public String getFio() {
		User user = getCurrentUser();		
		return user.getUserInfo().getFio();
	}
	
	/**
	 * Возвращает номер текущего пользователя.
	 * 
	 * @return Номер текущего пользователя.
	 */
	public String getNumber() {
		User user = getCurrentUser();
		return user.getUserInfo().getNumber();
	}
	
	/**
	 * Возвращает объект текущего пользователя.
	 * 
	 * @return Объект текущего пользователя.
	 */
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}
	
	/**
	 * Удаляет пользователя по его ИД.
	 * 
	 * @param id ИД пользователя, которого необходимо удалить.
	 */
	@Transactional
	public void deleteUserById(long id) {
		userRepo.deleteById(id);
	}
	
	/**
	 * Добавляет нового пользователя с указанными параметрами.
	 * 
	 * @param username Логин нового пользователя.
	 * @param password Пароль нового пользователя.
	 * @param fio ФИО нового пользователя.
	 * @param number Номер нового пользователя.
	 * @param authority Роль нового пользователя.
	 */
	@Transactional
	public void addUser(String username, String password, String fio, String number, String authority) {			
		User user = createUser(username, password, fio, number, authority);
		userRepo.save(user);
		
	}
	
	/**
	 * Создает и возвращает объект пользователя на основе переданных параметров.
	 * 
	 * @param username Логин нового пользователя.
	 * @param password Пароль нового пользователя.
	 * @param fio ФИО нового пользователя.
	 * @param number Номер нового пользователя.
	 * @param authority Роль нового пользователя.
	 * @return Объект пользователя.
	 */
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
