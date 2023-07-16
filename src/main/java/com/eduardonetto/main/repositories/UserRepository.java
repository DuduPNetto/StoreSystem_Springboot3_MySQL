package com.eduardonetto.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eduardonetto.main.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	List<User> findUserByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.name = ?1 AND u.email = ?2")
	User findUserByNameAndEmail(String name, String email);

}
