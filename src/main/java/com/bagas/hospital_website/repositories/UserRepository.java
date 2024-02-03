package com.bagas.hospital_website.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bagas.hospital_website.models.User;

/**
 * Репозиторий для доступа к сущностям User.
 * Расширяет интерфейс JpaRepository<User, Long>.
 */

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Находит пользователя по указанному логину.
	 * 
	 * @param username Логин.
	 * @return Optional, содержащий пользователя с указанным логином, если такой пользователь существует.
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Находит всех пользователей с ролью 'ROLE_USER' и сортирует по ФИО из связанной сущности UserInfo.
	 * 
	 * @return Список пользователей, отсортированных по ФИО.
	 */
	@Query("SELECT u FROM User u JOIN u.authorities a JOIN u.userInfo ui where a.authority = 'ROLE_USER' ORDER BY ui.fio")
	List<User> findAllUsersOrderByFio();
	
}
