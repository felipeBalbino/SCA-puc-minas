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

import br.com.sga.client.PlanoAcaoClient;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/planoacao")
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
	public ModelAndView PlanoAcao() {
		ModelAndView mv = new ModelAndView("/seguranca/planoacao/index");
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
			return "/seguranca/planoacao/index";
		}

		try {
			PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);

			if (planoacao.getCodigo() == null) {
				cliente.save(planoacao);
				attr.addFlashAttribute("mensagem", "Ação inserida com sucesso!");
			} else {
				cliente.update(planoacao, planoacao.getCodigo());
				attr.addFlashAttribute("mensagem", "Ação alterada com sucesso!");
			}
			return "redirect:/planoacao";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return "/seguranca/planoacao/index";
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		PlanoAcaoClient cliente = new PlanoAcaoClient(gateway, user, password);
		List<PlanoAcao> list = cliente.list();
		ModelAndView mv = new ModelAndView("/seguranca/planoacao/list");
		mv.addObject("list", list);
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
		ModelAndView mv = new ModelAndView("/seguranca/planoacao/aindex");
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
		attr.addFlashAttribute("mensagem", "Ação deletada com sucesso!");
		return "redirect:/seguranca/planoacao/list";
	}

	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "planoacao";
	}
}
