package com.eduardonetto.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.OrderProduct;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.OrderProductRepository;
import com.eduardonetto.main.services.OrderService;
import com.eduardonetto.main.services.ProductService;
import com.eduardonetto.main.services.UserService;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@Controller
@RequestMapping(value = "/order/")
public class FrontendOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderProductRepository repository;

	@GetMapping
	public String orders(Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "index";
	}

	@GetMapping("/find/")
	public String findById(Model model, @RequestParam(value = "id") Long id) {
		Order order = orderService.findById(id);
		model.addAttribute("orders", order);
		return "index";
	}

	@GetMapping("/register")
	public String registerOrder(Model model) {
		model.addAttribute("order", new Order());
		return "registerOrder";
	}

	@PostMapping("/create")
	public RedirectView createOrder(@ModelAttribute Order order) {
		User user = userService.findByNameAndEmail(order.getClient().getName(), order.getClient().getEmail());
		if (user != null) {
			order.setClient(user);
			orderService.insert(order);
		} else {
			throw new ObjectNotFoundException("Object not found with [name=" + order.getClient().getName() + ", email="
					+ order.getClient().getEmail());
		}
		return new RedirectView("/order/");
	}

	@GetMapping("/remove/")
	public RedirectView removeOrderProduct(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "oid") Long oid) {
		Order order = orderService.findById(oid);
		OrderProduct op = new OrderProduct(order, productService.findById(id), null, null);
		order.getProducts().remove(op);
		repository.delete(op);
		return new RedirectView("/order/");
	}

}
