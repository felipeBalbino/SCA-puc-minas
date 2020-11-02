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

import br.com.sga.model.TipoMetodo;
import br.com.sga.service.TipoMetodoService;

@RestController
@RequestMapping("/tipometodo")
public class TipoMetodoResource {

	@Autowired
	private TipoMetodoService tipoMetodoService;
		
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TipoMetodo>> findAll() {
		List<TipoMetodo> tipoAtivos = tipoMetodoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody TipoMetodo tipoMetodo) {
		tipoMetodo = tipoMetodoService.save(tipoMetodo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(tipoMetodo.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoMetodo> findById(@PathVariable("id") Long id) {
		TipoMetodo tipoMetodo = tipoMetodoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(tipoMetodo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {	
		tipoMetodoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TipoMetodo tipoMetodo, 
			@PathVariable("id") Long id) {
		tipoMetodo.setCodigo(id);
		tipoMetodoService.update(tipoMetodo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(tipoMetodo.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
}
