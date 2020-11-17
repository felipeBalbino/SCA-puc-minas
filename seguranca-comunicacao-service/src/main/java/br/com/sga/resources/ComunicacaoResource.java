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

import br.com.sga.model.Comunicacao;
import br.com.sga.model.PlanoAcao;
import br.com.sga.service.ComunicacaoService;

@RestController
@RequestMapping("/comunicacao")
public class ComunicacaoResource {

	@Autowired
	private ComunicacaoService service;

	/**
	 * @param communication
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<Void> save(@Valid @RequestBody PlanoAcao planoAcao) {
		Comunicacao comunicacao = service.save(planoAcao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comunicacao.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Comunicacao>> findAll() {
		List<Comunicacao> ativos = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}

}
