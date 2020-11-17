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

import br.com.sga.model.Barragem;
import br.com.sga.model.Sensor;
import br.com.sga.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorResource {

	@Autowired
	private SensorService sensorService;
		
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Sensor>> findAll() {
		List<Sensor> lista = sensorService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Sensor sensor) {
		sensor = sensorService.save(sensor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(sensor.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sensor> findById(@PathVariable("id") Long id) {
		Sensor sensor = sensorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(sensor);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {	
		sensorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Sensor sensor, 
			@PathVariable("id") Long id) {
		sensor.setCodigo(id);
		sensorService.update(sensor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(sensor.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/listbybarragem", method = RequestMethod.GET)
	public ResponseEntity<List<Sensor>> listbybarragem(@PathVariable("id") Long id) {
		List<Sensor> lista = sensorService.findByBarragem(id);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	
}
