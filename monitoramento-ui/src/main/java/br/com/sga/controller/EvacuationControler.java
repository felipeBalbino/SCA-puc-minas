package br.com.sga.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.client.Communication;
import br.com.sga.client.CommunicationClient;


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
    public String welcome(@PathVariable Long codigo, String user, RedirectAttributes attr) {
        
		CommunicationClient cliente = 
				new CommunicationClient(gateway, user, password);
		
		Communication communication = new Communication();
		communication.setDam(codigo);
		communication.setUser(user);
		cliente.save(communication);
		
		attr.addFlashAttribute("mensagem","Processo de evacuation iniciado com sucesso");
		return "redirect:/evacuation/";
    }

}
