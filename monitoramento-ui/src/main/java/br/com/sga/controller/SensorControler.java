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
import br.com.sga.client.SensorClient;
import br.com.sga.client.TipoMetodoClient;
import br.com.sga.client.TipoSensorClient;
import br.com.sga.dto.Ativo;
import br.com.sga.dto.DanoPotencial;
import br.com.sga.dto.Sensor;
import br.com.sga.dto.TipoMetodo;
import br.com.sga.dto.TipoSensor;

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

	public static final String URL_INDEX = "barragem/sensor/index";
	public static final String URL_LIST = "barragem/sensor/list";


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
			
			if(sensor.getCodigo() == null) {
				cliente.save(sensor);
				attr.addFlashAttribute("mensagem", "Sensor adicionado com sucesso!");
			}else {
				cliente.update(sensor, sensor.getCodigo());
				attr.addFlashAttribute("mensagem", "Sensor alterado com sucesso!");
			}

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
		for(Sensor sensor:list) {
			AtivosClient ativosClient = new AtivosClient(gateway, user, password);
			sensor.setAtivo(ativosClient.findById(sensor.getCodigoAtivo()));
		}
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
		return "sensor";
	}
}
