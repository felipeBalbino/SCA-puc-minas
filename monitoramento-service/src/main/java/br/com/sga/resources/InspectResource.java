package br.com.sga.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sga.model.Inspect;
import br.com.sga.repository.filter.InspectFilter;
import br.com.sga.service.InspectService;

/**
 * @author sga
 *
 */
@RestController
@RequestMapping("/inspect")
public class InspectResource {

	@Autowired
	private InspectService inspectService;

	/**	
	 * @param search
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Inspect>> list(@RequestParam(value = "search", required = false) String search) {
		List<Inspect> list;
		if (search == null) {
			list = inspectService.findAll();
		} else {
			InspectFilter InspectFilter = new InspectFilter();
			InspectFilter.setId(Long.parseLong(search));
			list = inspectService.search(InspectFilter);
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Inspect> findById(@PathVariable("id") Long id) {
		Inspect entidade = inspectService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		inspectService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
