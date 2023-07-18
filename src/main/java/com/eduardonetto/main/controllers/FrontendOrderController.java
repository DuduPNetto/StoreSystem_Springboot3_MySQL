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
import com.eduardonetto.main.controllers.query.UpdateOrderStatus;
import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.entities.OrderProduct;
import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.entities.enums.OrderStatus;
import com.eduardonetto.main.security.TokenService;
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

	@Autowired
	private TokenService tokenService;

	@GetMapping
	public String orders(Model model, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		model.addAttribute("orderStatus", new UpdateOrderStatus());
		model.addAttribute("productToAdd", new ProductToAdd());
		return "allOrders";
	}

	@GetMapping("/find/")
	public String findById(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		model.addAttribute("productToAdd", new ProductToAdd());
		Order order = orderService.findById(id);
		model.addAttribute("orders", order);
		return "allOrders";
	}

	@GetMapping("/register/")
	public String registerOrder(Model model, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		model.addAttribute("order", new Order());
		return "registerOrder";
	}

	@PostMapping("/create/")
	public RedirectView createOrder(Model model, @ModelAttribute Order order,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		User user = userService.findByNameAndEmail(order.getClient().getName(), order.getClient().getEmail());
		if (user != null) {
			order.setClient(user);
			order.setOrderStatus(OrderStatus.CREATED);
			orderService.insert(order);
		} else {
			throw new ObjectNotFoundException("Object not found with [name=" + order.getClient().getName() + ", email="
					+ order.getClient().getEmail());
		}
		return new RedirectView("/order/" + "?token=" + token);
	}

	@GetMapping("/remove/")
	public RedirectView removeOrderProduct(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "oid") Long oid, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		Order order = orderService.findById(oid);
		OrderProduct op = new OrderProduct(order, productService.findById(id), null, null);
		order.getProducts().remove(op);
		orderProductService.delete(op);
		return new RedirectView("/order/" + "?token=" + token);
	}

	@PostMapping("/add/")
	public RedirectView addOrderProduct(Model model, @RequestParam(value = "id") Long id,
			@ModelAttribute ProductToAdd productToAdd, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		try {
			Order order = orderService.findById(id);
			Product product = productService.findById(Long.parseLong(productToAdd.getProductId()));
			OrderProduct orderProduct = new OrderProduct(order, product, productToAdd.getQuantity(),
					product.getPrice());
			orderProductService.insert(orderProduct);
		} catch (NumberFormatException e) {
			throw new ObjectNotFoundException("Invalid Product ID. id=" + id);
		}
		return new RedirectView("/order/" + "?token=" + token);
	}

	@PostMapping("/update/")
	public RedirectView addOrderProduct(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token, @ModelAttribute UpdateOrderStatus updatedOrderStatus) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		try {
			OrderStatus orderStatus = OrderStatus.valueOf(updatedOrderStatus.getStatus().toUpperCase());
			Order order = orderService.findById(id);
			order.setOrderStatus(orderStatus);
			orderService.update(id, order);
		} catch (IllegalArgumentException e) {
			throw new ObjectNotFoundException("OrderStatus not found. value=" + updatedOrderStatus.getStatus());
		}
		return new RedirectView("/order/" + "?token=" + token);
	}

}
