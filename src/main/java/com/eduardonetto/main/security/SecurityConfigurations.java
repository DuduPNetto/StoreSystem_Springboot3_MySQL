package com.eduardonetto.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/").permitAll()
						.requestMatchers(HttpMethod.POST, "/login").permitAll()
						.requestMatchers(HttpMethod.GET, "/logout/").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/all/").permitAll()
						.requestMatchers(HttpMethod.POST, "/user/search_email/").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/remove/").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/register/").permitAll()
						.requestMatchers(HttpMethod.POST, "/user/create/").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/update/").permitAll()
						.requestMatchers(HttpMethod.POST, "/user/update/").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/update/").permitAll()
						.requestMatchers(HttpMethod.GET, "/product/").permitAll()
						.requestMatchers(HttpMethod.GET, "/product/all/").permitAll()
						.requestMatchers(HttpMethod.GET, "/product/register/").permitAll()
						.requestMatchers(HttpMethod.POST, "/product/create/").permitAll()
						.requestMatchers(HttpMethod.GET, "/product/update/").permitAll()
						.requestMatchers(HttpMethod.POST, "/product/update/").permitAll()
						.requestMatchers(HttpMethod.GET, "/product/remove/").permitAll()
						.requestMatchers(HttpMethod.POST, "/product/search_name/").permitAll()
						.requestMatchers(HttpMethod.GET, "/order/").permitAll()
						.requestMatchers(HttpMethod.GET, "/order/find/").permitAll()
						.requestMatchers(HttpMethod.GET, "/order/register/").permitAll()
						.requestMatchers(HttpMethod.POST, "/order/create/").permitAll()
						.requestMatchers(HttpMethod.GET, "/order/remove/").permitAll()
						.requestMatchers(HttpMethod.POST, "/order/add/").permitAll()
						.anyRequest().hasRole("ADMIN"))
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
