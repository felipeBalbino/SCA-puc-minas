package br.com.sga.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.client.ComunicacaoClient;
import br.com.sga.dto.Acao;
import br.com.sga.dto.Comunicacao;


@Controller
@RequestMapping({"/evacuacao"})
public class EvacuationControler {
	
	@RequestMapping
	public String search() {
		return "evacuacao";	
	}
	
	@Value("${zuul.ws.gateway}")	
    private String gateway;
	
	@Value("${zuul.ws.user}")
    private String user;
	
	@Value("${zuul.ws.password}")
    private String password;
	
	@RequestMapping(value ="{codigo}", method =RequestMethod.POST)
    public String evacuation(@PathVariable Long codigo, String user, RedirectAttributes attr) {
        
		ComunicacaoClient cliente = 
				new ComunicacaoClient(gateway, user, password);
		
		Comunicacao comunicacao = new Comunicacao();
		comunicacao.setAcao(new Acao(codigo));
		cliente.save(comunicacao);
		
		attr.addFlashAttribute("mensagem","Evacuation process started successfully");
		return "/evacuacao/emsgEvacuacao";
    }

}
