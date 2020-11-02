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

import br.com.sga.client.DanoPotencialClient;
import br.com.sga.client.SensorClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.model.DanoPotencial;
import br.com.sga.model.Sensor;
import br.com.sga.model.TipoMetodo;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/sensor")
public class SensorControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String URL_INDEX = "/barragem/sensor/index";
	public static final String URL_LIST = "/barragem/sensor/list";

	/**
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView sensor() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new Sensor());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated Sensor sensor, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			SensorClient cliente = new SensorClient(gateway, user, password);
			cliente.save(sensor);
			attr.addFlashAttribute("mensagem", "Sensor inserida com sucesso");
			return "redirect:/sensor";
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
		SensorClient cliente = new SensorClient(gateway, user, password);
		List<Sensor> list = cliente.list();
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("sensores", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Long codigo) {
		SensorClient cliente = new SensorClient(gateway, user, password);
		Sensor sensor = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(sensor);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		SensorClient cliente = new SensorClient(gateway, user, password);
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Barragem deletada com sucesso");
		return "redirect:/sensor/";
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
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "sensor";
	}
}
