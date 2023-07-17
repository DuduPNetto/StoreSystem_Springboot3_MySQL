package com.eduardonetto.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.eduardonetto.main.controllers.query.Token;
import com.eduardonetto.main.security.TokenService;

@Controller
@RequestMapping("/")
public class FrontendIndexController {

	@Autowired
	private TokenService service;

	@GetMapping("/")
	public String index(Model model, @RequestParam(name = "token", defaultValue = "") String token) {
		if (model.getAttribute("token") == null) {
			model.addAttribute("token", new Token());
		}
		if (token.trim().length() > 0) {
			service.validateToken(token);
			model.addAttribute("tokenValue", token);
		}
		return "index";	
	}

	@PostMapping("/login")
	public String login(Model model, @ModelAttribute Token token) {
		service.validateToken(token.getContent());
		model.addAttribute("tokenValue", token.getContent());
		return "index";
	}

	@GetMapping("/logout/")
	public RedirectView logout(Model model) {
		if (model.getAttribute("tokenValue") != null) {
			model.addAttribute("tokenValue", "");
		}
		return new RedirectView("/");
	}

}
