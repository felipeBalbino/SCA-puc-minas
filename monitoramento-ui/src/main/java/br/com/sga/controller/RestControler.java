package br.com.sga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sga.client.ManutencaoClient;
import br.com.sga.dto.Manutencao;

/**
 * @author sga
 *
 */	
@RestController
@RequestMapping("/rest/api")
public class RestControler {
	
	@Value("${zuul.ws.gateway}")
	private String gateway;

	@Value("${zuul.ws.user}")
	private String user;

	@Value("${zuul.ws.password}")
	private String password;
	
	
	/**	
	 * @return
	 */
	@GetMapping("/listaManutencoes")
	public List<Manutencao> listaManutencaos() {
		ManutencaoClient cliente = new ManutencaoClient(gateway, user, password);
		return cliente.list();
	}
	
}
