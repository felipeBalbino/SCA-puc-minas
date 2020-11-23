package br.com.sga.controller;

import java.text.SimpleDateFormat;
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
import br.com.sga.client.ComunicacaoClient;
import br.com.sga.client.PlanoAcaoClient;
import br.com.sga.dto.Comunicacao;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/comunicacao")
public class ComunicacaoControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;
	
	static final String URL_INDEX= "/seguranca/comunicacao/index";
	static final String URL_LIST= "/seguranca/comunicacao/list";

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
	public ModelAndView comunicacoes() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Comunicacao());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Comunicacao comunicacao, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			ComunicacaoClient cliente = new ComunicacaoClient(gateway, user, password);
			
			if(comunicacao.getCodigo() == null) {
				cliente.save(comunicacao);
				attr.addFlashAttribute("mensagem", "Comunicacao adicionado com sucesso!");
			}else {
				cliente.update(comunicacao, comunicacao.getCodigo());
				attr.addFlashAttribute("mensagem", "Comunicacao alterado com sucesso!");
			}
			
			return "redirect:/comunicacao";
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
		ComunicacaoClient cliente = new ComunicacaoClient(gateway, user, password);
		List<Comunicacao> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("comunicacoes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		ComunicacaoClient cliente = new ComunicacaoClient(gateway, user, password);
		Comunicacao comunicacao = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(comunicacao);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		ComunicacaoClient cliente = new ComunicacaoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Comunicacao deletado com sucesso!");
		return "redirect:/comunicacao";
	}

	
	/**	
	 * @return
	 */
	@ModelAttribute("listaComunicacoes")
	public List<Comunicacao> listaComunicacoes() {
		ComunicacaoClient cliente = new ComunicacaoClient(gateway, user, password);
		return cliente.list();
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("listaPlanoAcao")
	public List<PlanoAcao> listaPlanoAcao() {
		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		return cliente.list();
	}
	
	
	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "comunicacao";
	}
}
