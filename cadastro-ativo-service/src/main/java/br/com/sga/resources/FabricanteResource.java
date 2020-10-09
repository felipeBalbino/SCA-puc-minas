package br.com.sga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sga.model.Ativo;
import br.com.sga.service.FabricanteService;

@RestController
@RequestMapping("/fabricante")
public class FabricanteResource {

	@Autowired
	private FabricanteService fabricanteService;
		
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Ativo>> findAll() {
		List<Ativo> ativos = fabricanteService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
}
