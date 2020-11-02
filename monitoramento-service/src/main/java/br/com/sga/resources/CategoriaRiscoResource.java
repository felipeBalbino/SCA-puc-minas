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

import br.com.sga.model.CategoriaRisco;
import br.com.sga.service.CategoriaRiscoService;

@RestController
@RequestMapping("/categoriarisco")
public class CategoriaRiscoResource {

	@Autowired
	private CategoriaRiscoService categoriaRiscoService;

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CategoriaRisco>> findAll() {
		List<CategoriaRisco> tipoAtivos = categoriaRiscoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CategoriaRisco categoriaRisco) {
		categoriaRisco = categoriaRiscoService.save(categoriaRisco);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaRisco.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoriaRisco> findById(@PathVariable("id") Long id) {
		CategoriaRisco categoriaRisco = categoriaRiscoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRisco);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		categoriaRiscoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaRisco categoriaRisco, @PathVariable("id") Long id) {
		categoriaRisco.setCodigo(id);
		categoriaRiscoService.update(categoriaRisco);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaRisco.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
