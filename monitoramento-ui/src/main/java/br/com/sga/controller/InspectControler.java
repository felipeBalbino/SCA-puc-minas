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

import br.com.sga.binder.DamPropertyEditor;
import br.com.sga.client.DamClient;
import br.com.sga.client.InspectClient;
import br.com.sga.model.Dam;
import br.com.sga.model.Inspect;
import br.com.sga.model.Method;
import br.com.sga.model.PotentialDamage;
import br.com.sga.repository.filter.InspectFilter;

/**
 * @author sga
 *
 */	
@Controller
@RequestMapping("/inspect")
public class InspectControler {

	@Value("${zuul.ws.gateway}")
	private String gatemay;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String CADASTRO_VIEW = "/inspect/inspect";

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DamClient cliente = new DamClient(gatemay, user, password);
		binder.registerCustomEditor(Dam.class, new DamPropertyEditor(cliente));
	}

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView insert() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Inspect());
		return mv;
	}

	/**
	 * @param inspect
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Inspect inspect, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			InspectClient cliente = new InspectClient(gatemay, user, password);
			cliente.save(inspect);
			attr.addFlashAttribute("mensagem", "Inspect saved successfully");
			return "redirect:/inspect/new";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("data", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") InspectFilter filter) {
		InspectClient cliente = new InspectClient(gatemay, user, password);

		List<Inspect> list = cliente.list(filter.getId());
		ModelAndView mv = new ModelAndView("/inspect/list");
		mv.addObject("inpecoes", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		InspectClient cliente = new InspectClient(gatemay, user, password);
		Inspect Inspect = cliente.findById(codigo);

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(Inspect);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		InspectClient cliente = new InspectClient(gatemay, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Inspect successfully deleted");
		return "redirect:/inspect/";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listDams")
	public List<Dam> listDams() {
		DamClient cliente = new DamClient(gatemay, user, password);
		List<Dam> list = cliente.list(null);
		return list;
	}	

	/**	
	 * @return
	 */
	@ModelAttribute("listMethods")
	public List<Method> listMethods() {
		return Arrays.asList(Method.values());
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("listPotentialDamages")
	public List<PotentialDamage> listPotentialDamages() {
		return Arrays.asList(PotentialDamage.values());
	}
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "inspect";
	}
}
