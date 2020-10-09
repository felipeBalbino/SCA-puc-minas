package br.com.sga.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sga.model.Communication;
import br.com.sga.resources.filter.CommunicationFilter;
import br.com.sga.service.CommunicationService;

@RestController
@RequestMapping("/communication")
public class CommunicationResource {

	@Autowired
	private CommunicationService service;

	/**
	 * @param communication
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CommunicationFilter communicationFilter) {
		Communication test = new Communication();
		test.setDam(communicationFilter.getDam());
		test.setUser(communicationFilter.getUser());
		test = service.save(test);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(test.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<br.com.sga.model.Communication>> findAll() {
		List<br.com.sga.model.Communication> ativos = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}

}
