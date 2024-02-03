package com.bagas.hospital_website.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_website.models.Role;
import com.bagas.hospital_website.repositories.RoleRepository;

/**
 * Класс RoleService отвечает за управление операциями, связанными с ролями пользователей в системе.
 * Взаимодействует с RoleRepository для доступа и управления данными ролей в базе данных.
 */

@Service
public class RoleService {

	/**
	 * Репозиторий RoleRepository для доступа и управления данными ролей в базе данных.
	 */
	@Autowired
	private RoleRepository roleRepo;
	
	/**
	 * Возвращает список всех ролей.
	 * 
	 * @return Список ролей.
	 */
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}
	
	/**
	 * Получает роль по её наименованию (authority).
	 * 
	 * @param authority Наименование роли, которую необходимо получить.
	 * @return Объект роли.
	 */
	public Role getByAuthority(String authority) {
		return roleRepo.findByAuthority(authority);
	}
}

