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
			model.addAttribute("error", "Usuário ou Senha invalidos");

		if (logout != null)
			model.addAttribute("message", "Logado com sucesso");

		return "auth-login";
	}

}