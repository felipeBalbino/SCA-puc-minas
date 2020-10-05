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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sga.model.Dam;
import br.com.sga.model.Inspect;
import br.com.sga.repository.filter.DamFilter;
import br.com.sga.service.DamService;
import br.com.sga.service.InspectService;

/**
 * @author sga
 *
 */
@RestController
@RequestMapping("/dam")
public class DamResource {

	@Autowired
	private DamService damService;
	@Autowired
	private InspectService InspectService;

	/**
	 * @param search
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Dam>> list(@RequestParam(value = "search", required = false) String search) {
		List<Dam> dams;
		if (search == null) {
			dams = damService.findAll();
		} else {
			DamFilter filter = new DamFilter();
			filter.setDescription(search);
			dams = damService.search(filter);
		}
		return ResponseEntity.status(HttpStatus.OK).body(dams);
	}

	/**
	 * @param ativos
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Dam dam) {
		dam = damService.save(dam);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dam.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Dam> findById(@PathVariable("id") Long id) {
		Dam ativo = damService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		damService.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param dam
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Dam dam, @PathVariable("id") Long id) {
		dam.setId(id);
		damService.update(dam);

		return ResponseEntity.noContent().build();
	}

	/**
	 * @param inspect
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/inspect", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInspect(@Valid @RequestBody Inspect inspect, @PathVariable("id") Long id) {
		inspect.setDam(damService.findById(id));
		inspect = InspectService.save(inspect);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/inspect/{id}").buildAndExpand(inspect.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param inspect
	 * @param id
	 * @param idSolicitacao
	 * @return
	 */
	@RequestMapping(value = "/{id}/inspect/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateInspect(@RequestBody Inspect inspect, @PathVariable("id") Long id,
			@PathVariable("idSolicitacao") Long idSolicitacao) {
		inspect.setDam(damService.findById(id));
		inspect.setId(idSolicitacao);
		InspectService.update(inspect);

		return ResponseEntity.noContent().build();
	}
}
