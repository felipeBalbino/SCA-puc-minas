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

import br.com.sga.model.Manutencao;
import br.com.sga.service.AtivosService;
import br.com.sga.service.ManutencaoService;

@RestController
@RequestMapping("/manutencao")
public class ManutencaoResource {

	@Autowired
	private ManutencaoService manutencaoService;
	
	@Autowired
	private AtivosService ativosService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Manutencao>> findAll() {
		List<Manutencao> lista = manutencaoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Manutencao> findById(@PathVariable("id") Long id) {
		Manutencao entidade = manutencaoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		manutencaoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Manutencao manutencao,
			@PathVariable("id") Long id ) {
		manutencao.setAtivo(ativosService.findById(id));
		manutencao = manutencaoService.save(manutencao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/manutencao/{id}").buildAndExpand(manutencao.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/manutencao", method = RequestMethod.GET)
	public ResponseEntity<List<Manutencao>> findByAtivoId(@PathVariable("id") Long id) {
		List<Manutencao> lista = manutencaoService.findByAtivo_Codigo(id);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@RequestMapping(value = "/{id}/manutencao/{idManutencao}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Manutencao manutencao, 
			@PathVariable("id") Long id, @PathVariable("idManutencao") Long idManutencao) {
		manutencao.setAtivo(ativosService.findById(id));
		manutencao.setCodigo(idManutencao);
		manutencaoService.update(manutencao);
		
		return ResponseEntity.noContent().build();
	}

}
