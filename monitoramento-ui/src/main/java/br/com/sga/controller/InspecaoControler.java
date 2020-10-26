package br.com.sga.controller;

import java.util.Arrays;
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

import br.com.sga.binder.BarragemPropertyEditor;
import br.com.sga.client.BarragemClient;
import br.com.sga.client.InspecaoClient;
import br.com.sga.model.Barragem;
import br.com.sga.model.DanoPotencial;
import br.com.sga.model.Inspecao;
import br.com.sga.model.Metodo;

/**
 * @author sga
 *
 */	
@Controller
@RequestMapping("/inspecao")
public class InspecaoControler {

	@Value("${zuul.ws.gateway}")
	private String gatemay;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String URL_INDEX = "/inspecao/index";
	public static final String URL_LIST = "/inspecao/list";


	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		BarragemClient cliente = new BarragemClient(gatemay, user, password);
		binder.registerCustomEditor(Barragem.class, new BarragemPropertyEditor(cliente));
	}

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView insert() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Inspecao());
		return mv;
	}

	/**
	 * @param inspecao
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Inspecao inspecao, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			InspecaoClient cliente = new InspecaoClient(gatemay, user, password);
			cliente.save(inspecao);
			attr.addFlashAttribute("mensagem", "Inspeção salva com sucesso");
			return "redirect:/inspecao/new";
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
		InspecaoClient cliente = new InspecaoClient(gatemay, user, password);

		List<Inspecao> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("inpecoes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		InspecaoClient cliente = new InspecaoClient(gatemay, user, password);
		Inspecao inspecao = cliente.findById(codigo);

		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(inspecao);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		InspecaoClient cliente = new InspecaoClient(gatemay, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Inspeção deletada com sucesso");
		return "redirect:/inspecao/";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listBarragens")
	public List<Barragem> listBarragens() {
		BarragemClient cliente = new BarragemClient(gatemay, user, password);
		List<Barragem> list = cliente.list();
		return list;
	}	

	/**	
	 * @return
	 */
	@ModelAttribute("listaMetodos")
	public List<Metodo> listaMetodos() {
		return Arrays.asList(Metodo.values());
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("listaDanoPotencial")
	public List<DanoPotencial> listaDanoPotencial() {
		return Arrays.asList(DanoPotencial.values());
	}
	
	/**	
	 * @return
	 */	
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "inspecao";
	}
}
