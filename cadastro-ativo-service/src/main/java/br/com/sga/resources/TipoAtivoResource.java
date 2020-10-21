package br.com.sga.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sga.model.Fabricante;
import br.com.sga.model.TipoAtivo;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody TipoAtivo tipoAtivos) {
		tipoAtivos = tipoAtivoService.save(tipoAtivos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(tipoAtivos.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoAtivo> findById(@PathVariable("id") Long id) {
		TipoAtivo tipoAtivo = tipoAtivoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {	
		tipoAtivoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TipoAtivo tipoAtivo, 
			@PathVariable("id") Long id) {
		tipoAtivo.setCodigo(id);
		tipoAtivoService.update(tipoAtivo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(tipoAtivo.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
}
