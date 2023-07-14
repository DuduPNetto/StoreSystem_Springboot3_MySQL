package com.eduardonetto.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.ProductRepository;
import com.eduardonetto.main.repositories.UserRepository;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Alex Green", "alex@gmail.com", "55 999999999");
		User user2 = new User(null, "Bob Grey", "bob@gmail.com", "55 999999999");
		User user3 = new User(null, "Alex Blue", "alex2@gmail.com", "55 999999999");
		User user4 = new User(null, "Bob Brown", "bob2@gmail.com", "55 999999999");
		User user5 = new User(null, "Alex Red", "alex3@gmail.com", "55 999999999");

		Product p1 = new Product(null, "Product 1", 19.90);
		Product p2 = new Product(null, "Product 2", 29.90);
		Product p3 = new Product(null, "Product 3", 5.40);
		Product p4 = new Product(null, "Product 4", 14.70);
		Product p5 = new Product(null, "Product 5", 39.00);

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

	}

}
