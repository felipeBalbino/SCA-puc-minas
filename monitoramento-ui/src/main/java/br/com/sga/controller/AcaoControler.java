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

import br.com.sga.client.AcaoClient;
import br.com.sga.client.PessoaClient;
import br.com.sga.client.PlanoAcaoClient;
import br.com.sga.dto.Acao;
import br.com.sga.dto.Pessoa;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/acao")
public class AcaoControler {

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
	public ModelAndView acao() {
		ModelAndView mv = new ModelAndView("/seguranca/acao/index");
		mv.addObject(new Acao());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Acao acao, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return "/seguranca/acao/index";
		}

		try {
			AcaoClient cliente = new AcaoClient(gateway, user, password);

			if (acao.getCodigo() == null) {
				cliente.save(acao);
				attr.addFlashAttribute("mensagem", "Ação inserida com sucesso!");
			} else {
				cliente.update(acao, acao.getCodigo());
				attr.addFlashAttribute("mensagem", "Ação alterada com sucesso!");
			}
			return "redirect:/acao";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return "/seguranca/acao/index";
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		AcaoClient cliente = new AcaoClient(gateway, user, password);
		List<Acao> list = cliente.list();
		ModelAndView mv = new ModelAndView("/seguranca/acao/list");
		mv.addObject("list", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		AcaoClient cliente = new AcaoClient(gateway, user, password);
		Acao acao = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView("/seguranca/acao/aindex");
		mv.addObject(acao);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		AcaoClient cliente = new AcaoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Ação deletada com sucesso!");
		return "redirect:/seguranca/acao/list";
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
	@ModelAttribute("listaPessoas")
	public List<Pessoa> listaPessoas() {
		PessoaClient cliente = new PessoaClient(gateway, user, password);
		return cliente.list();
	}

	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "acao";
	}
}
