package br.com.sga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sga.client.DamClient;
import br.com.sga.model.Dam;
import br.com.sga.repository.filter.InspectFilter;

@Controller
@RequestMapping({ "/", "/home" })
public class HomeControler {


	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;

	@RequestMapping
	public String home(@ModelAttribute("filter") InspectFilter filter, Authentication authentication) {
		authentication.getAuthorities();
		return "home";
	}

	@ModelAttribute("listDams")
	public List<Dam> listDams() {
		DamClient cliente = new DamClient(gateway, user, password);
		List<Dam> list = cliente.list();
		return list;
	}
	
	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "home";
	}
}	
