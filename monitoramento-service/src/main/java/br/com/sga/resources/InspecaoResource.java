package br.com.sga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
