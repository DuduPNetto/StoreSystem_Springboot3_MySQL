package com.eduardonetto.main.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eduardonetto.main.entities.AuthUser;
import com.eduardonetto.main.services.exceptions.DatabaseException;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String generateToken(AuthUser authUser) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer("auth-api").withSubject(authUser.getUsername())
					.withExpiresAt(genExpirationDate()).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new DatabaseException("Error while generation token " + e.getMessage());
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
		} catch (JWTVerificationException e) {
			throw new DatabaseException("Token is not valid.");
		}
	}

	private Instant genExpirationDate() {
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
	}

}
