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

import br.com.sga.model.Inspecao;
import br.com.sga.service.InspecaoService;

/**
 * @author sga
 *
 */
@RestController
@RequestMapping("/inspecao")
public class InspecaoResource {

	@Autowired
	private InspecaoService inspecaoService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Inspecao inspecao) {
		inspecao = inspecaoService.save(inspecao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(inspecao.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param search
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Inspecao>> list() {
		List<Inspecao> list = inspecaoService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Inspecao> findById(@PathVariable("id") Long id) {
		Inspecao entidade = inspecaoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		inspecaoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Inspecao inspecao, @PathVariable("id") Long id) {
		inspecao.setCodigo(id);
		inspecaoService.update(inspecao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(inspecao.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/ultima", method = RequestMethod.GET)
	public ResponseEntity<Inspecao> findByUltimaInspecao(@PathVariable("id") Long id) {
		Inspecao entidade = inspecaoService.findByUltimaInspecao(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}

}
