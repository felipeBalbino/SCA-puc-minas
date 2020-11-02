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

import br.com.sga.model.DanoPotencial;
import br.com.sga.service.DanoPotencialService;

@RestController
@RequestMapping("/danopotencial")
public class DanoPotencialResource {

	@Autowired
	private DanoPotencialService danoPotencialService;

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DanoPotencial>> findAll() {
		List<DanoPotencial> tipoAtivos = danoPotencialService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody DanoPotencial danoPotencial) {
		danoPotencial = danoPotencialService.save(danoPotencial);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(danoPotencial.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DanoPotencial> findById(@PathVariable("id") Long id) {
		DanoPotencial danoPotencial = danoPotencialService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(danoPotencial);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		danoPotencialService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody DanoPotencial danoPotencial, @PathVariable("id") Long id) {
		danoPotencial.setCodigo(id);
		danoPotencialService.update(danoPotencial);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(danoPotencial.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
