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

import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.UserService;

@Controller
@RequestMapping("/user/")
public class FrontendUserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "registerUser";
	}

	@GetMapping("/all")
	public String allUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "allUsers";
	}

	@PostMapping("/create")
	public String userCreated(@ModelAttribute User user) {
		userService.insert(user);
		return "userChanged";
	}

	@GetMapping("/update/")
	public String updateUser(Model model, @RequestParam(value = "id") Long id) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "updateUser";
	}

	@PostMapping("/update")
	public String userUpdate(@ModelAttribute User user) {
		userService.update(user.getId(), user);
		return "userChanged";
	}

	@GetMapping("/remove/")
	public RedirectView removeUser(Model model, @RequestParam(value = "id") Long id) {
		userService.delete(id);
		return new RedirectView("/user/all");
	}

}
