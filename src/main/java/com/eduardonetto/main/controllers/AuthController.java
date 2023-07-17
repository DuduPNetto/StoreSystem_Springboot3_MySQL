package com.eduardonetto.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardonetto.main.controllers.dto.auth.AuthUserDTO;
import com.eduardonetto.main.controllers.dto.auth.LoginResponseDTO;
import com.eduardonetto.main.controllers.dto.auth.RegisterDTO;
import com.eduardonetto.main.entities.AuthUser;
import com.eduardonetto.main.repositories.AuthUserRepository;
import com.eduardonetto.main.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private AuthUserRepository repository;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthUserDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = authManager.authenticate(usernamePassword);
		String token = tokenService.generateToken((AuthUser) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
		if (repository.findByUsername(data.username()) != null) {
			return ResponseEntity.badRequest().build();
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		AuthUser authUser = new AuthUser(data.username(), encryptedPassword, data.name(), data.email(), data.role());

		repository.save(authUser);
		return ResponseEntity.ok().build();
	}

}
