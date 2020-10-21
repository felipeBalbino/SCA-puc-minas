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
	public ResponseEntity<List<Fabricante>> findAll() {
		List<Fabricante> fabricantes = fabricanteService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(fabricantes);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Fabricante fabricante) {
		fabricante = fabricanteService.save(fabricante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(fabricante.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Fabricante> findById(@PathVariable("id") Long id) {
		Fabricante fabricante = fabricanteService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(fabricante);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {	
		fabricanteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Fabricante fabricante, 
			@PathVariable("id") Long id) {
		fabricante.setCodigo(id);
		fabricanteService.update(fabricante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(fabricante.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
}
