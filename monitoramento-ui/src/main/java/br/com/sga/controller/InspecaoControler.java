package br.com.sga.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import br.com.sga.binder.CategoriaRiscoPropertyEditor;
import br.com.sga.binder.DanoPotencialPropertyEditor;
import br.com.sga.binder.LenientDateParser;
import br.com.sga.client.BarragemClient;
import br.com.sga.client.CategoriaRiscoClient;
import br.com.sga.client.DanoPotencialClient;
import br.com.sga.client.InspecaoClient;
import br.com.sga.client.LeituraSensorClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.dto.Barragem;
import br.com.sga.dto.CategoriaRisco;
import br.com.sga.dto.DanoPotencial;
import br.com.sga.dto.Inspecao;
import br.com.sga.dto.TipoMetodo;

/**
 * @author sga
 *
 */	
@Controller
@RequestMapping("/inspecao")
public class InspecaoControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String URL_INDEX = "barragem/inspecao/index";
	public static final String URL_LIST = "barragem/inspecao/list";
	

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		BarragemClient cliente = new BarragemClient(gateway, user, password);
		binder.registerCustomEditor(Barragem.class, new BarragemPropertyEditor(cliente));
		
		CategoriaRiscoClient categoriaRiscoClient = new CategoriaRiscoClient(gateway, user, password);
		binder.registerCustomEditor(CategoriaRisco.class, new CategoriaRiscoPropertyEditor(categoriaRiscoClient));
		
		DanoPotencialClient danoPotencialClient = new DanoPotencialClient(gateway, user, password);
		binder.registerCustomEditor(DanoPotencial.class, new DanoPotencialPropertyEditor(danoPotencialClient));
		

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//2020-11-22T15:51
	    dateTimeFormat.setLenient(false);
	     
		binder.registerCustomEditor(Date.class,new LenientDateParser(dateTimeFormat, 
			     true));
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
			
			InspecaoClient cliente = new InspecaoClient(gateway, user, password);
			
			if(inspecao.getCodigo() == null) {
				cliente.save(inspecao);
				attr.addFlashAttribute("mensagem", "Inspeção adicionado com sucesso!");
			}else {
				cliente.update(inspecao, inspecao.getCodigo());
				attr.addFlashAttribute("mensagem", "Inspeção alterado com sucesso!");
			}
			
			return "redirect:/inspecao/";
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
		InspecaoClient cliente = new InspecaoClient(gateway, user, password);

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
		InspecaoClient cliente = new InspecaoClient(gateway, user, password);
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
		InspecaoClient cliente = new InspecaoClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Inspeção deletada com sucesso");
		return "redirect:/inspecao/";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listBarragens")
	public List<Barragem> listBarragens() {
		BarragemClient cliente = new BarragemClient(gateway, user, password);
		List<Barragem> list = cliente.list();
		return list;
	}	

	/**	
	 * @return
	 */
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
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "inspecao";
	}
}
