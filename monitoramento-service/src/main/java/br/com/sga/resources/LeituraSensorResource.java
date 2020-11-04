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

import br.com.sga.model.LeituraSensor;
import br.com.sga.service.LeituraSensorService;

@RestController
@RequestMapping("/sensor/leitura")
public class LeituraSensorResource {

	@Autowired
	private LeituraSensorService leituraSensorService;
		
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<LeituraSensor>> findAll() {
		List<LeituraSensor> tipoAtivos = leituraSensorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody LeituraSensor leituraSensor) {
		leituraSensor = leituraSensorService.save(leituraSensor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(leituraSensor.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LeituraSensor> findById(@PathVariable("id") Long id) {
		LeituraSensor leituraSensor = leituraSensorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(leituraSensor);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {	
		leituraSensorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody LeituraSensor leituraSensor, 
			@PathVariable("id") Long id) {
		leituraSensor.setCodigo(id);
		leituraSensorService.update(leituraSensor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(leituraSensor.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/{id}/leiturasensor",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<LeituraSensor>> findBySensor(@PathVariable("id") Long id) {
		List<LeituraSensor> tipoAtivos = leituraSensorService.findBySensor(id);
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}
	
	
}
