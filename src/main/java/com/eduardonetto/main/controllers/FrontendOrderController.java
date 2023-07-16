package com.eduardonetto.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardonetto.main.entities.Order;
import com.eduardonetto.main.services.OrderService;

@Controller
@RequestMapping(value = "/order/")
public class FrontendOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public String index(Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "index";
	}

}