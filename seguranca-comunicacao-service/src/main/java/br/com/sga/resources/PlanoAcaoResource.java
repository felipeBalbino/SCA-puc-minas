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

import br.com.sga.model.PlanoAcao;
import br.com.sga.service.PlanoAcaoService;

@RestController
@RequestMapping("/planoacao")
public class PlanoAcaoResource {

	@Autowired
	private PlanoAcaoService service;

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PlanoAcao>> findAll() {
		List<PlanoAcao> list = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	/**
	 * @param planoAcao
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody PlanoAcao planoAcao) {
		planoAcao = service.save(planoAcao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planoAcao.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlanoAcao> findById(@PathVariable("id") Long id) {
		PlanoAcao ativo = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param planoAcao
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody PlanoAcao planoAcao, @PathVariable("id") Long id) {
		planoAcao.setCodigo(id);
		service.update(planoAcao);

		return ResponseEntity.noContent().build();
	}

}
