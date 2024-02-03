package com.bagas.hospital_website.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagas.hospital_website.models.Role;

/**
 * Репозиторий для доступа к сущностям Role.
 * Расширяет интерфейс JpaRepository<Role, Long>.
 */

public interface RoleRepository extends JpaRepository<Role, Long>{

	/**
	 * Находит роль по ее наименованию (authority).
	 * 
	 * @param authority Наименование роли.
	 * @return Роль
	 */
	Role findByAuthority(String authority);
}
