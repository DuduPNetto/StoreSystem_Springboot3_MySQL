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

import com.eduardonetto.main.controllers.query.Search;
import com.eduardonetto.main.controllers.util.URL;
import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.security.TokenService;
import com.eduardonetto.main.services.ProductService;

@Controller
@RequestMapping("/product/")
public class FrontendProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/register/")
	public String registerProduct(Model model, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		model.addAttribute("product", new Product());
		return "registerProduct";
	}

	@GetMapping("/all/")
	public String allProducts(Model model, @RequestParam(value = "token") String token) {
		List<Product> products = productService.findAll();
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		model.addAttribute("products", products);
		model.addAttribute("search", new Search());
		return "allProducts";
	}

	@GetMapping("/")
	public String findById(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "findProduct";
	}

	@PostMapping("/create/")
	public String productCreated(Model model, @ModelAttribute Product product,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		productService.insert(product);
		return "productChanged";
	}

	@GetMapping("/update/")
	public String updateProduct(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "updateProduct";
	}

	@PostMapping("/update/")
	public String productUpdate(Model model, @ModelAttribute Product product,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		productService.update(product.getId(), product);
		return "productChanged";
	}

	@GetMapping("/remove/")
	public RedirectView removeProduct(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		productService.delete(id);
		return new RedirectView("/product/all/" + "?token=" + token);
	}

	@PostMapping("/search_name/")
	public String searchByName(Model model, @ModelAttribute Search search,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		String content = URL.decodeParam(search.getContent());
		List<Product> products = productService.findByName(content);
		model.addAttribute("products", products);
		return "allProducts";
	}

}
