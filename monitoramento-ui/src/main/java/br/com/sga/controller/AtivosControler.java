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
import br.com.sga.client.FabricanteClient;
import br.com.sga.client.TipoAtivoClient;
import br.com.sga.model.Ativo;
import br.com.sga.model.Fabricante;
import br.com.sga.model.TipoAtivo;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/ativos")
public class AtivosControler {

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
	public ModelAndView ativos() {
		ModelAndView mv = new ModelAndView("/ativos/ativos");
		mv.addObject(new Ativo());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Ativo ativo, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return "/ativos/ativos";
		}

		try {
			AtivosClient cliente = new AtivosClient(gateway, user, password);
			
			if(ativo.getCodigo() == null) {
				cliente.save(ativo);
				attr.addFlashAttribute("mensagem", "Ativo inserido com sucesso!");
			}else {
				cliente.update(ativo, ativo.getCodigo());
				attr.addFlashAttribute("mensagem", "Ativo update com sucesso!");
			}
			return "redirect:/ativos";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return "/ativos/ativos";
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		List<Ativo> list = cliente.list();
		ModelAndView mv = new ModelAndView("/ativos/list");
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
		ModelAndView mv = new ModelAndView("/ativos/ativos");
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
		attr.addFlashAttribute("mensagem", "ativos successfully deleted");
		return "redirect:/ativos/";
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
	@ModelAttribute("listaTipoAtivo")
	public List<TipoAtivo> listaTipoAtivo() {
		TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
		return cliente.list();
	}
	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "ativos";
	}
}
