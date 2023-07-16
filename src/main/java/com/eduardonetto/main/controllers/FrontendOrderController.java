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

import com.eduardonetto.main.controllers.query.ProductToAdd;
import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.OrderProduct;
import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.OrderProductService;
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
	private OrderProductService orderProductService;

	@GetMapping
	public String orders(Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("productToAdd", new ProductToAdd());
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
		orderProductService.delete(op);
		return new RedirectView("/order/");
	}

	@PostMapping("/add/")
	public RedirectView addOrderProduct(Model model, @RequestParam(value = "id") Long id,
			@ModelAttribute ProductToAdd productToAdd) {
		try {
			Order order = orderService.findById(id);
			Product product = productService.findById(Long.parseLong(productToAdd.getProductId()));
			OrderProduct orderProduct = new OrderProduct(order, product, productToAdd.getQuantity(), product.getPrice());
			orderProductService.insert(orderProduct);
		} catch (NumberFormatException e) {
			throw new ObjectNotFoundException("Invalid Product ID. id=" + id);
		}
		return new RedirectView("/order/");
	}

}
