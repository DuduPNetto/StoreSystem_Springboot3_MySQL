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
import com.eduardonetto.main.security.TokenService;
import com.eduardonetto.main.services.UserService;
import com.eduardonetto.main.services.exceptions.DatabaseException;

@Controller
@RequestMapping("/user/")
public class FrontendUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@GetMapping("/register/")
	public String registerUser(Model model, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("user", new User());
		model.addAttribute("tokenValue", token);
		return "registerUser";
	}

	@GetMapping("/all/")
	public String allUsers(Model model, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("search", new Search());
		return "allUsers";
	}

	@GetMapping("/")
	public String findById(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "findUser";
	}

	@PostMapping("/create/")
	public String userCreated(Model model, @ModelAttribute User user, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
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
	public String updateUser(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "updateUser";
	}

	@PostMapping("/update/")
	public String userUpdate(Model model, @ModelAttribute User user, @RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		userService.update(user.getId(), user);
		return "userChanged";
	}

	@GetMapping("/remove/")
	public RedirectView removeUser(Model model, @RequestParam(value = "id") Long id,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		userService.delete(id);
		return new RedirectView("/user/all/" + "?token=" + token);
	}

	@PostMapping("/search_email/")
	public String searchByEmail(Model model, @ModelAttribute Search search,
			@RequestParam(value = "token") String token) {
		tokenService.validateToken(token);
		model.addAttribute("tokenValue", token);
		String content = URL.decodeParam(search.getContent());
		List<User> users = userService.findByEmail(content);
		model.addAttribute("users", users);
		return "allUsers";
	}

}
