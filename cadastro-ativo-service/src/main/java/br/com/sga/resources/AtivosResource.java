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

import br.com.sga.model.Ativo;
import br.com.sga.model.Manutencao;
import br.com.sga.service.AtivosService;
import br.com.sga.service.ManutencaoService;

@RestController
@RequestMapping("/ativos")
public class AtivosResource {

	@Autowired
	private AtivosService ativosService;

	
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Ativo>> findAll() {
		List<Ativo> ativos = ativosService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Ativo ativos) {
		ativos = ativosService.save(ativos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ativos.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ativo> findById(@PathVariable("id") Long id) {
		Ativo ativo = ativosService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		ativosService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Ativo ativo, 
			@PathVariable("id") Long id) {
		ativo.setCodigo(id);
		ativosService.update(ativo);
		
		return ResponseEntity.noContent().build();
	}
	

}
