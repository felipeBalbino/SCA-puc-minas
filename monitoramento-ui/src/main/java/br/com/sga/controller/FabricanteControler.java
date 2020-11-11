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

import br.com.sga.client.FabricanteClient;
import br.com.sga.dto.Fabricante;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/fabricante")
public class FabricanteControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;
	
	static final String URL_INDEX= "/ativos/fabricante/index";
	static final String URL_LIST= "/ativos/fabricante/list";

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView ativos() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Fabricante());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Fabricante fabricante, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			FabricanteClient cliente = new FabricanteClient(gateway, user, password);
			
			if(fabricante.getCodigo() == null) {
				cliente.save(fabricante);
				attr.addFlashAttribute("mensagem", "Fabricante adicionado com sucesso!");
			}else {
				cliente.update(fabricante, fabricante.getCodigo());
				attr.addFlashAttribute("mensagem", "Fabricante alterado com sucesso!");
			}
			
			return "redirect:/fabricante";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return URL_INDEX;
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		FabricanteClient cliente = new FabricanteClient(gateway, user, password);
		List<Fabricante> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("fabricantes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		FabricanteClient cliente = new FabricanteClient(gateway, user, password);
		Fabricante fabricante = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(fabricante);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		FabricanteClient cliente = new FabricanteClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Fabricante deletado com sucesso!");
		return "redirect:/fabricante";
	}

	
	/**	
	 * @return
	 */
	@ModelAttribute("listaFabricantes")
	public List<Fabricante> listaFabricantes() {
		FabricanteClient cliente = new FabricanteClient(gateway, user, password);
		return cliente.list();
	}
	
	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "fabricante";
	}
}
