package br.com.sga.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.client.CommunicationClient;
import br.com.sga.client.filter.CommunicationFilter;


@Controller
@RequestMapping({"/evacuation"})
public class EvacuationControler {
	
	@RequestMapping
	public String search() {
		return "evacuation";	
	}
	
	@Value("${zuul.ws.gateway}")	
    private String gateway;
	
	@Value("${zuul.ws.user}")
    private String user;
	
	@Value("${zuul.ws.password}")
    private String password;
	
	@RequestMapping(value ="{codigo}", method =RequestMethod.POST)
    public String evacuation(@PathVariable Long codigo, String user, RedirectAttributes attr) {
        
		CommunicationClient cliente = 
				new CommunicationClient(gateway, user, password);
		
		CommunicationFilter communication = new CommunicationFilter();
		communication.setBarragem(codigo);
		communication.setUser(user);
		cliente.save(communication);
		
		attr.addFlashAttribute("mensagem","Evacuation process started successfully");
		return "/evacuation/evacuation";
    }

}
