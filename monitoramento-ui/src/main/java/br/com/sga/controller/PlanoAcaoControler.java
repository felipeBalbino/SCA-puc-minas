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
import br.com.sga.binder.PessoaPropertyEditor;
import br.com.sga.binder.PlanoAcaoPropertyEditor;
import br.com.sga.client.AtivosClient;
import br.com.sga.client.BarragemClient;
import br.com.sga.client.PessoaClient;
import br.com.sga.client.PlanoAcaoClient;
import br.com.sga.dto.Ativo;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.GrauRiscoEnum;
import br.com.sga.dto.Pessoa;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/planoacao")
public class PlanoAcaoControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	static final String URL_INDEX = "/seguranca/planoacao/index";
	static final String URL_LIST = "/seguranca/planoacao/list";

	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		binder.registerCustomEditor(PlanoAcao.class, new PlanoAcaoPropertyEditor(cliente));
		
		PessoaClient pessoaClient = new PessoaClient(gateway, user, password);
		binder.registerCustomEditor(Pessoa.class, new PessoaPropertyEditor(pessoaClient));
		
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
		mv.addObject(new PlanoAcao());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated PlanoAcao planoacao, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);

			if (planoacao.getCodigo() == null) {
				cliente.save(planoacao);
				attr.addFlashAttribute("mensagem", "PlanoAcao adicionado com sucesso!");
			} else {
				cliente.update(planoacao, planoacao.getCodigo());
				attr.addFlashAttribute("mensagem", "PlanoAcao alterado com sucesso!");
			}

			return "redirect:/planoacao";
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
		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		List<PlanoAcao> list = cliente.list();
		for(PlanoAcao planoAcao:list) {
			AtivosClient ativosClient = new AtivosClient(gateway, user, password);
			planoAcao.setAtivo(ativosClient.findById(planoAcao.getCodigoAtivo()));
		}
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("acoes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		PlanoAcao planoacao = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(planoacao);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Plano Acao deletado com sucesso!");
		return "redirect:/planoacao";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listAtivos")
	public List<Ativo> listAtivos() {
		AtivosClient ativosClient = new AtivosClient(gateway, user, password);
		List<Ativo> list = ativosClient.list();
		return list;
	}	
	
	/**
	 * @return
	 */
	@ModelAttribute("listPessoas")
	public List<Pessoa> listPessoas() {
		PessoaClient cliente = new PessoaClient(gateway, user, password);
		List<Pessoa> list = cliente.list();
		return list;
	}	
	
	/**
	 * @return
	 */	
	@ModelAttribute("listaGrauRisco")
	public List<GrauRiscoEnum> listaGrauRisco() {	
		return Arrays.asList(GrauRiscoEnum.values());
	}

	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "planoacao";
	}
}
