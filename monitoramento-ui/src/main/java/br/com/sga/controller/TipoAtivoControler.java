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

import br.com.sga.client.TipoAtivoClient;
import br.com.sga.model.TipoAtivo;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/tipoAtivo")
public class TipoAtivoControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;
	
	static final String URL_INDEX= "/ativos/tipoAtivo/index";
	static final String URL_LIST= "/ativos/tipoAtivo/list";

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView ativos() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new TipoAtivo());
		return mv;
	}

	/**
	 * @param dam
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated TipoAtivo tipoAtivo, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
			
			if(tipoAtivo.getCodigo() == null) {
				cliente.save(tipoAtivo);
				attr.addFlashAttribute("mensagem", "TipoAtivo adicionado com sucesso!");
			}else {
				cliente.update(tipoAtivo, tipoAtivo.getCodigo());
				attr.addFlashAttribute("mensagem", "TipoAtivo alterado com sucesso!");
			}
			
			return "redirect:/tipoAtivo";
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
		TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
		List<TipoAtivo> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("tipos", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
		TipoAtivo tipoAtivo = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(tipoAtivo);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "TipoAtivo deletado com sucesso!");
		return "redirect:/tipoAtivo";
	}

	
	/**	
	 * @return
	 */
	@ModelAttribute("listaTipoAtivos")
	public List<TipoAtivo> listaTipoAtivos() {
		TipoAtivoClient cliente = new TipoAtivoClient(gateway, user, password);
		return cliente.list();
	}
	
	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "tipoAtivo";
	}
}
