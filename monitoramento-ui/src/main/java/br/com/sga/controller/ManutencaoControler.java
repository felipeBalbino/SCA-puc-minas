package br.com.sga.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.binder.LenientDateParser;
import br.com.sga.client.AtivosClient;
import br.com.sga.client.ManutencaoClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.dto.Ativo;
import br.com.sga.dto.Manutencao;
import br.com.sga.dto.StatusManutencaoEnum;
import br.com.sga.dto.TipoManutencaoEnum;
import br.com.sga.dto.TipoMetodo;

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
	
	static final String URL_INDEX= "/ativos/manutencao/index";
	static final String URL_LIST= "/ativos/manutencao/list";

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//2020-11-22T15:51
	    dateTimeFormat.setLenient(false);
	     
		binder.registerCustomEditor(Date.class,new LenientDateParser(dateTimeFormat, 
			     true));
	}
	
	
	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView manutencao() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Manutencao());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Manutencao manutencao, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
			
			if(manutencao.getCodigo() == null) {
				cliente.save(manutencao);
				attr.addFlashAttribute("mensagem", "Manutenção adicionado com sucesso!");
			}else {
				cliente.update(manutencao, manutencao.getCodigo());
				attr.addFlashAttribute("mensagem", "Manutenção alterado com sucesso!");
			}
			
			return "redirect:/manutencao";
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
		ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
		List<Manutencao> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("manutencoes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
		Manutencao manutencao = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(manutencao);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Manutenção deletado com sucesso!");
		return "redirect:/manutencao";
	}

	
	/**	
	 * @return
	 */
	@ModelAttribute("listaManutencoes")
	public List<Manutencao> listaManutencaos() {
		ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
		return cliente.list();
	}
	
	/**
	 * @return
	 */	
	@ModelAttribute("listaTipoManutencao")
	public List<TipoManutencaoEnum> listTipoManutencao() {	
		return Arrays.asList(TipoManutencaoEnum.values());
	}
	
	/**
	 * @return
	 */	
	@ModelAttribute("listaStatusManutencao")
	public List<StatusManutencaoEnum> listStatusManutencao() {	
		return Arrays.asList(StatusManutencaoEnum.values());
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("listaAtivos")
	public List<Ativo> listaAtivos() {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		return cliente.list();
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
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "manutencao";
	}
}
