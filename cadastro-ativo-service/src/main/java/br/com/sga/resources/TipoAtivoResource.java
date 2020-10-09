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
import br.com.sga.model.TipoAtivo;
import br.com.sga.service.FabricanteService;
import br.com.sga.service.TipoAtivoService;

@RestController
@RequestMapping("/tipoativo")
public class TipoAtivoResource {

	@Autowired
	private TipoAtivoService tipoAtivoService;
		
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TipoAtivo>> findAll() {
		List<TipoAtivo> tipoAtivos = tipoAtivoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}
	
}
