package br.com.sga.controller;

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

import br.com.sga.client.AtivosClient;
import br.com.sga.model.Ativo;
import br.com.sga.repository.filter.AtivosFilter;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/manutencao")
public class ManutencaoControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	final static String URL_DEFAULT = "/ativos/manutencao/manutencao";
	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView manutencao() {
		ModelAndView mv = new ModelAndView(URL_DEFAULT);
		mv.addObject(new Ativo());
		return mv;
	}

	/**
	 * @param dam
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Ativo ativo, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_DEFAULT;
		}

		try {
			AtivosClient cliente = new AtivosClient(gateway, user, password);
			cliente.save(ativo);
			attr.addFlashAttribute("mensagem", "ativos successfully saved");
			return "redirect:/manutencao";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return URL_DEFAULT;
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") AtivosFilter filter) {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		List<Ativo> list = cliente.list(filter.getDescription());
		ModelAndView mv = new ModelAndView("/ativos/manutenao/list");
		mv.addObject("ativos", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		Ativo ativo = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_DEFAULT);
		mv.addObject(ativo);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		AtivosClient cliente = new AtivosClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "manutencao successfully deleted");
		return "redirect:/manutencao/";
	}

	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "manutencao";
	}
}
