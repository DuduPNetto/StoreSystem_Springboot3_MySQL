package com.eduardonetto.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.UserRepository;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Alex Green", "alex@gmail.com", "55 999999999");
		User user2 = new User(null, "Bob Grey", "bob@gmail.com", "55 999999999");

		userRepository.saveAll(Arrays.asList(user1, user2));

	}

}
