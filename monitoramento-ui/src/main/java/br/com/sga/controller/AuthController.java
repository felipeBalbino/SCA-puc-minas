package br.com.sga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sga
 *
 */
@Controller
public class AuthController {

	/**
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Username or password is invalid");

		if (logout != null)
			model.addAttribute("message", "Successfully logged in");

		return "/auth/login";
	}

}