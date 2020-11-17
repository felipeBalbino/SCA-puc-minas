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
import br.com.sga.client.BarragemClient;
import br.com.sga.client.DanoPotencialClient;
import br.com.sga.client.FabricanteClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.dto.Ativo;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.DanoPotencial;
import br.com.sga.dto.Sensor;
import br.com.sga.dto.TipoMetodo;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/barragem")
public class BarragemControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String URL_INDEX = "/barragem/index";
	public static final String URL_LIST = "/barragem/list";

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView barragem() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Barragem());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Barragem barragem, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			
			BarragemClient cliente = new BarragemClient(gateway, user, password);
			
			if(barragem.getCodigo() == null) {
				cliente.save(barragem);
				attr.addFlashAttribute("mensagem", "Barragem adicionado com sucesso!");
			}else {
				cliente.update(barragem, barragem.getCodigo());
				attr.addFlashAttribute("mensagem", "Barragem alterado com sucesso!");
			}
			
			return "redirect:/barragem";
			
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return URL_INDEX;
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		BarragemClient cliente = new BarragemClient(gateway, user, password);
		List<Barragem> list = cliente.list();
		
		for(Barragem barragem:list) {
			AtivosClient ativosClient = new AtivosClient(gateway, user, password);
			barragem.setAtivo(ativosClient.findById(barragem.getCodigoAtivo()));
		}
		
		
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("barragens", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		BarragemClient cliente = new BarragemClient(gateway, user, password);
		Barragem barragem = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(barragem);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		BarragemClient cliente = new BarragemClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Barragem deletada com sucesso");
		return "redirect:/barragem/";
	}

	@ModelAttribute("listaTipoMetodos")
	public List<TipoMetodo> listaTipoMetodos() {
		TipoMetodoClient cliente = new TipoMetodoClient(gateway, user, password);
		List<TipoMetodo> list = cliente.list();
		return list;
	}
	
	/**
	 * @return
	 */
	@ModelAttribute("listaDanoPotencial")
	public List<DanoPotencial> listaDanoPotencial() {
		DanoPotencialClient cliente = new DanoPotencialClient(gateway, user, password);
		List<DanoPotencial> list = cliente.list();
		return list;
	}
	
	/**
	 * @return
	 */
	@ModelAttribute("listaCategoriaRisco")
	public List<DanoPotencial> listaCategoriaRisco() {
		DanoPotencialClient cliente = new DanoPotencialClient(gateway, user, password);
		List<DanoPotencial> list = cliente.list();
		return list;
	}
	

	/**
	 * @return
	 */
	@ModelAttribute("listaAtivos")
	public List<Ativo> listaAtivos() {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		List<Ativo> list = cliente.list();
		return list;
	}
	

	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "barragem";
	}
}
