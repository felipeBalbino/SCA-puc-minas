package br.com.sga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.client.BarragemClient;
import br.com.sga.client.SensorClient;
import br.com.sga.dto.Barragem;
import br.com.sga.repository.filter.InspecaoFilter;

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
	public String home(@ModelAttribute("filter") InspecaoFilter filter, Authentication authentication, RedirectAttributes attr) {
		authentication.getAuthorities();
		Barragem barragem = null;
		if(filter != null && filter.getId() != null) {
			BarragemClient barragemClient = new BarragemClient(gateway, user, password);
			barragem = barragemClient.findById(Long.parseLong(filter.getId()));
		}else {
			List<Barragem> list = listBarragens();
			barragem = (list.isEmpty()?null:listBarragens().get(0));
			if(barragem != null) {
				filter.setId(barragem.getCodigo().toString());
				SensorClient sensorClient = new SensorClient(gateway, user, password);
				attr.addFlashAttribute("sensores",sensorClient.findByBarragem(barragem.getCodigo()));
			}

		}
		if(barragem != null) {
			attr.addFlashAttribute("barragem",barragem);
		}
		return "home";
	}

	@ModelAttribute("listBarragens")
	public List<Barragem> listBarragens() {
		BarragemClient cliente = new BarragemClient(gateway, user, password);
		List<Barragem> list = cliente.list();
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
