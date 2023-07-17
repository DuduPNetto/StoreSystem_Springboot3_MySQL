package com.eduardonetto.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.eduardonetto.main.entities.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

	UserDetails findByUsername(String username);

}
