package br.com.sga.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.client.DamClient;
import br.com.sga.model.Dam;
import br.com.sga.model.Method;
import br.com.sga.model.PotentialDamage;
import br.com.sga.repository.filter.DamFilter;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/dam")
public class DamControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView dam() {
		ModelAndView mv = new ModelAndView("/dam/dam");
		mv.addObject(new Dam());
		return mv;
	}

	/**
	 * @param dam
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Dam dam, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return "/dam/dam";
		}

		try {
			DamClient cliente = new DamClient(gateway, user, password);
			cliente.save(dam);
			attr.addFlashAttribute("mensagem", "Dam successfully saved");
			return "redirect:/dam";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return "/dam/dam";
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") DamFilter filter) {
		DamClient cliente = new DamClient(gateway, user, password);
		List<Dam> list = cliente.list(filter.getDescription());
		ModelAndView mv = new ModelAndView("/dam/list");
		mv.addObject("dams", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		DamClient cliente = new DamClient(gateway, user, password);
		Dam dam = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView("/dam/dam");
		mv.addObject(dam);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		DamClient cliente = new DamClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Dam successfully deleted");
		return "redirect:/dam/";
	}

	/**
	 * @return
	 */	
	@ModelAttribute("listMethods")
	public List<Method> listMethods() {	
		return Arrays.asList(Method.values());
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("listPotentialDamages")
	public List<PotentialDamage> listPotentialDamages() {
		return Arrays.asList(PotentialDamage.values());
	}
}
