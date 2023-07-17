package com.eduardonetto.main.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

	/*
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Autowired private ProductRepository productRepository;
	 * 
	 * @Autowired private OrderRepository orderRepository;
	 * 
	 * @Autowired private OrderProductRepository orderProductRepository;
	 */

	@Override
	public void run(String... args) throws Exception {

		/*
		 * User user1 = new User(null, "Alex Green", "alex@gmail.com", "55 999999999");
		 * User user2 = new User(null, "Bob Grey", "bob@gmail.com", "55 999999999");
		 * User user3 = new User(null, "Alex Blue", "alex2@gmail.com", "55 999999999");
		 * User user4 = new User(null, "Bob Brown", "bob2@gmail.com", "55 999999999");
		 * User user5 = new User(null, "Alex Red", "alex@gmail.com", "55 999999999");
		 * 
		 * Product p1 = new Product(null, "Product 1", "High quality product", 19.90);
		 * Product p2 = new Product(null, "Product 2", "Good for work", 29.90); Product
		 * p3 = new Product(null, "Product 3", "Natural", 5.40); Product p4 = new
		 * Product(null, "Product 4", "Hand made", 14.70); Product p5 = new
		 * Product(null, "Product 5", "Delicious", 39.00);
		 * 
		 * Order o1 = new Order(null, user1); Order o2 = new Order(null, user2); Order
		 * o3 = new Order(null, user1); Order o4 = new Order(null, user5);
		 * 
		 * userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		 * orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		 * productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		 * 
		 * OrderProduct op1 = new OrderProduct(o1, p1, 10, p1.getPrice()); OrderProduct
		 * op2 = new OrderProduct(o2, p3, 4, p3.getPrice()); OrderProduct op3 = new
		 * OrderProduct(o1, p5, 6, p5.getPrice()); OrderProduct op4 = new
		 * OrderProduct(o1, p2, 2, p2.getPrice()); OrderProduct op5 = new
		 * OrderProduct(o3, p4, 1, p2.getPrice()); OrderProduct op6 = new
		 * OrderProduct(o2, p1, 7, p2.getPrice());
		 * 
		 * orderProductRepository.saveAll(Arrays.asList(op1, op2, op3, op4, op5, op6));
		 */

	}

}
