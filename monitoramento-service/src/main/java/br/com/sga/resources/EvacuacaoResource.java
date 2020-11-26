package br.com.sga.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.queue.ComunicacaoSender;

/**
 * @author sga
 *
 */
@RestController
@RequestMapping("/evacuacao")
public class EvacuacaoResource {


	@Autowired
	private ComunicacaoSender senderQueue;

	/**
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> evacuarBarragem(@Valid @RequestBody ComunicacaoDTO planoAcao) {
		
		planoAcao.setTipoComunicacao("EVACUAR");
		senderQueue.send(planoAcao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planoAcao.getCodigo())
		.toUri();
		
		return ResponseEntity.created(uri).build();
	}

	
}
