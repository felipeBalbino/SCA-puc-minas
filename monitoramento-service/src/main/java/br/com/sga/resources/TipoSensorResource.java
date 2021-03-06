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

import br.com.sga.model.TipoSensor;
import br.com.sga.service.TipoSensorService;

@RestController
@RequestMapping("/tiposensor")
public class TipoSensorResource {

	@Autowired
	private TipoSensorService tipoSensorService;

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TipoSensor>> findAll() {
		List<TipoSensor> tipoAtivos = tipoSensorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tipoAtivos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody TipoSensor tipoSensor) {
		tipoSensor = tipoSensorService.save(tipoSensor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tipoSensor.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoSensor> findById(@PathVariable("id") Long id) {
		TipoSensor tipoSensor = tipoSensorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(tipoSensor);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		tipoSensorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TipoSensor tipoSensor, @PathVariable("id") Long id) {
		tipoSensor.setCodigo(id);
		tipoSensorService.update(tipoSensor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tipoSensor.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
