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

import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.services.ProductService;

@Controller
@RequestMapping("/product/")
public class FrontendProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/register")
	public String registerProduct(Model model) {
		model.addAttribute("product", new Product());
		return "registerProduct";
	}

	@GetMapping("/all")
	public String allProducts(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "allProducts";
	}

	@PostMapping("/create")
	public String productCreated(@ModelAttribute Product product) {
		productService.insert(product);
		return "productChanged";
	}

	@GetMapping("/update/")
	public String updateProduct(Model model, @RequestParam(value = "id") Long id) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "updateProduct";
	}

	@PostMapping("/update")
	public String productUpdate(@ModelAttribute Product product) {
		productService.update(product.getId(), product);
		return "productChanged";
	}

	@GetMapping("/remove/")
	public RedirectView removeProduct(Model model, @RequestParam(value = "id") Long id) {
		productService.delete(id);
		return new RedirectView("/product/all");
	}

}
