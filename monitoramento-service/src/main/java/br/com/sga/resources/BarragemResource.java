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
import br.com.sga.model.Inspecao;
import br.com.sga.service.BarragemService;
import br.com.sga.service.InspecaoService;

/**
 * @author sga
 *
 */
@RestController
@RequestMapping("/barragem")
public class BarragemResource {

	@Autowired
	private BarragemService barragemService;
	@Autowired
	private InspecaoService InspecaoService;

	/**
	 * @param search
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Barragem>> list() {
		List<Barragem> barragens = barragemService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(barragens);
	}

	/**
	 * @param Barragem
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Barragem barragem) {
		barragem = barragemService.save(barragem);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(barragem.getCodigo()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Barragem> findById(@PathVariable("id") Long id) {
		Barragem ativo = barragemService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		barragemService.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param barragem
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Barragem barragem, @PathVariable("id") Long id) {
		barragem.setCodigo(id);
		barragemService.update(barragem);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(barragem.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param inspecao
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/inspecao", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInspecao(@Valid @RequestBody Inspecao inspecao, @PathVariable("id") Long id) {
		inspecao.setBarragem(barragemService.findById(id));
		inspecao = InspecaoService.save(inspecao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/inspecao/{id}").buildAndExpand(inspecao.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @param inspecao
	 * @param id
	 * @param idManutencao
	 * @return
	 */
	@RequestMapping(value = "/{id}/inspecao/{idManutencao}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateInspecao(@RequestBody Inspecao inspecao, @PathVariable("id") Long id,
			@PathVariable("idManutencao") Long idManutencao) {
		inspecao.setBarragem(barragemService.findById(id));
		inspecao.setCodigo(idManutencao);
		InspecaoService.update(inspecao);

		return ResponseEntity.noContent().build();
	}
}
