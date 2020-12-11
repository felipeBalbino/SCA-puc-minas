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
import br.com.sga.client.AtivosClient;
import br.com.sga.client.DanoPotencialClient;
import br.com.sga.client.LeituraSensorClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.client.TipoSensorClient;
import br.com.sga.dto.Ativo;
import br.com.sga.dto.DanoPotencial;
import br.com.sga.dto.LeituraSensor;
import br.com.sga.dto.TipoMetodo;
import br.com.sga.dto.TipoSensor;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/sensor/leitura")
public class LeituraSensorControler {

	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	public static final String URL_INDEX = "barragem/sensor/leitura/index";
	public static final String URL_LIST = "barragem/sensor/leitura/list";

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
	public ModelAndView leituraSensor() {
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(new LeituraSensor());
		return mv;
	}

	/**
	 * @param barragem
	 * @param erros
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String insert(@Validated LeituraSensor leituraSensor, Errors erros, RedirectAttributes attr) {

		if (erros.hasErrors()) {
			return URL_INDEX;
		}

		try {
			
			LeituraSensorClient cliente = new LeituraSensorClient(gateway, user, password);
			
			if(leituraSensor.getCodigo() == null) {
				cliente.save(leituraSensor);
				attr.addFlashAttribute("mensagem", "Leitura de Sensor adicionado com sucesso!");
			}else {
				cliente.update(leituraSensor, leituraSensor.getCodigo());
				attr.addFlashAttribute("mensagem", "Leitura de Sensor alterado com sucesso!");
			}
			
			return "redirect:/sensor/leitura";
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
		LeituraSensorClient cliente = new LeituraSensorClient(gateway, user, password);
		List<LeituraSensor> list = cliente.list();
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
		LeituraSensorClient cliente = new LeituraSensorClient(gateway, user, password);
		LeituraSensor leituraSensor = cliente.findById(codigo);
		ModelAndView mv = new ModelAndView(URL_INDEX);
		mv.addObject(leituraSensor);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {

		LeituraSensorClient cliente = new LeituraSensorClient(gateway, user, password);
		LeituraSensor leituraSensor = cliente.findById(codigo);
		
		cliente.delete(codigo);
		attr.addFlashAttribute("mensagem", "Leitura de sensor deletada com sucesso");
		return "redirect:/sensor/leitura/"+leituraSensor.getSensor().getCodigo()+"/leiturasensor";
	}

	
	
	/**
	 * @param filter
	 * @return
	 */
	@RequestMapping(value = "{codigo}/leiturasensor", method = RequestMethod.GET)
	public ModelAndView listBySensor(@PathVariable Long codigo) {
		LeituraSensorClient cliente = new LeituraSensorClient(gateway, user, password);
		List<LeituraSensor> list = cliente.listBySensor(codigo);
		ModelAndView mv = new ModelAndView(URL_LIST);
		mv.addObject("sensores", list);
		return mv;
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
	@ModelAttribute("listaTipoSensor")
	public List<TipoSensor> listaTipoSensor() {
		TipoSensorClient cliente = new TipoSensorClient(gateway, user, password);
		List<TipoSensor> list = cliente.list();
		return list;
	}

	/**
	 * @return
	 */
	@ModelAttribute("listaAtivos")
	public List<Ativo> listaAtivos() {
		AtivosClient cliente = new AtivosClient(gateway, user, password);
		List<Ativo> list = cliente.list();
		return list;
	}
	
	/**
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "leiturasensor";
	}
}
