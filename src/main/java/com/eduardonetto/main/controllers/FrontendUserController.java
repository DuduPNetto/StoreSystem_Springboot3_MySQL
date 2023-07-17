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
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.UserService;
import com.eduardonetto.main.services.exceptions.DatabaseException;

@Controller
@RequestMapping("/user/")
public class FrontendUserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register/")
	public String registerUser(Model model, @RequestParam(value = "token") String token) {
		if (!token.trim().equals("")) {
			model.addAttribute("user", new User());
			return "registerUser";
		}
		throw new DatabaseException("You are not logged");
	}

	@GetMapping("/all/")
	public String allUsers(Model model, @RequestParam(value = "token") String token) {
		if (!token.trim().equals("")) {
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			model.addAttribute("search", new Search());
			return "allUsers";
		}
		throw new DatabaseException("You are not logged");
	}

	@GetMapping("/")
	public String findById(Model model, @RequestParam(value = "id") Long id) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "findUser";
	}

	@PostMapping("/create/")
	public String userCreated(@ModelAttribute User user) {
		List<User> list = userService.findAll();
		for (User u : list) {
			if (u.equals(user)) {
				throw new DatabaseException("Object already exists");
			}
		}
		userService.insert(user);
		return "userChanged";
	}

	@GetMapping("/update/")
	public String updateUser(Model model, @RequestParam(value = "id") Long id) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "updateUser";
	}

	@PostMapping("/update/")
	public String userUpdate(@ModelAttribute User user) {
		userService.update(user.getId(), user);
		return "userChanged";
	}

	@GetMapping("/remove/")
	public RedirectView removeUser(Model model, @RequestParam(value = "id") Long id) {
		userService.delete(id);
		return new RedirectView("/user/all/");
	}

	@PostMapping("/search_email/")
	public String searchByEmail(Model model, @ModelAttribute Search search) {
		String content = URL.decodeParam(search.getContent());
		List<User> users = userService.findByEmail(content);
		model.addAttribute("users", users);
		return "allUsers";
	}

}
