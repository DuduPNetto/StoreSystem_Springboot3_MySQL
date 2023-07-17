package com.eduardonetto.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardonetto.main.controllers.query.Token;
import com.eduardonetto.main.security.TokenService;

@Controller
@RequestMapping("/")
public class FrontendIndexController {

	@Autowired
	private TokenService service;

	@GetMapping
	public String index(Model model) {
		if (model.getAttribute("authenticated") == null) {
			model.addAttribute("authenticated", false);
			model.addAttribute("token", new Token());
		}
		return "index";
	}

	@PostMapping("/login")
	public String login(Model model, @ModelAttribute Token token) {
		service.validateToken(token.getContent());
		model.addAttribute("authenticated", true);
		model.addAttribute("token", token);
		System.out.println(model.getAttribute("token"));
		return "index";
	}

}
