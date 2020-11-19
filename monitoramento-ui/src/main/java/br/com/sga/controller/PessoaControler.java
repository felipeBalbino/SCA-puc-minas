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

import br.com.sga.client.PessoaClient;
import br.com.sga.dto.Pessoa;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/pessoa")
public class PessoaControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	static final String URL_INDEX = "/seguranca/pessoa/index";
	static final String URL_LIST = "/seguranca/pessoa/list";

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView comunicacoes() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Pessoa());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Pessoa pessoa, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			PessoaClient cliente = new PessoaClient(gateway, user, password);

			if (pessoa.getCodigo() == null) {
				cliente.save(pessoa);
				attr.addFlashAttribute("mensagem", "Pessoa adicionado com sucesso!");
			} else {
				cliente.update(pessoa, pessoa.getCodigo());
				attr.addFlashAttribute("mensagem", "Pessoa alterado com sucesso!");
			}

			return "redirect:/pessoa";
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
		PessoaClient cliente = new PessoaClient(gateway, user, password);
		List<Pessoa> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("pessoas", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		PessoaClient cliente = new PessoaClient(gateway, user, password);
		Pessoa pessoa = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(pessoa);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		PessoaClient cliente = new PessoaClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Pessoa deletado com sucesso!");
		return "redirect:/pessoa";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listaComunicacoes")
	public List<Pessoa> listaComunicacoes() {
		PessoaClient cliente = new PessoaClient(gateway, user, password);
		return cliente.list();
	}

	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "pessoa";
	}
}
