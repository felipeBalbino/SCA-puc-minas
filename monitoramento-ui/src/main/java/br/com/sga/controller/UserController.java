package br.com.sga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.model.Role;
import br.com.sga.model.User;
import br.com.sga.service.UserService;
import br.com.sga.validator.UserValidator;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	/**
	 * @return
	 */
	@GetMapping("/new")
	public ModelAndView user() {
		ModelAndView mv = new ModelAndView("/user/new");
		mv.addObject(new User());
		return mv;
	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") User user) {
		ModelAndView mv = new ModelAndView("/user/new");
		mv.addObject(user);
		return mv;
	}

	/**
	 * @param user
	 * @param bindingResult
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String user(User user, BindingResult bindingResult, RedirectAttributes attr) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			return "/user/new";
		}
		userService.save(user);
		attr.addFlashAttribute("mensagem", "User has been saved successfully");
		return "redirect:/user/new";
	}

	/**
	 * @return
	 */
	@RequestMapping
	public ModelAndView search() {
		List<User> lista = userService.findAll();
		ModelAndView mv = new ModelAndView("/user/list");
		mv.addObject("usuarios", lista);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		userService.delete(codigo);
		attr.addFlashAttribute("mensagem", "User has been deleted successfully");
		return "redirect:/user/";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listaRoles")
	public List<Role> todosStatusTitulo() {
		return userService.listarRoles();
	}

}