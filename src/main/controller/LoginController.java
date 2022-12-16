package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.model.Users;
import main.service.UsersService;

@Controller
public class LoginController {

	@Autowired
	private UsersService usesrservice;

	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("/forbidden")
	public String showForbiddenError() {
		return "403";

	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("users", new Users());
		return "form-signup";
	}

	@PostMapping("processSignup")
	public String processSignup(@ModelAttribute Users users, RedirectAttributes redirectAttributes) {
		boolean errors = false;

		if (!users.getPassword().equals(users.getConfirmePassword())) {
			redirectAttributes.addAttribute("differentPasswords", "Passwords are different");
			errors = true;
		}

		if (usesrservice.loginExists(users.getLogin())) {
			redirectAttributes.addAttribute("loginExists", "Login already exists in the database");
			errors = true;
		}

		if (errors) {
			return "redirect:/signup";
		}

		usesrservice.createNewAccount(users);
		return "login";
	}

}
