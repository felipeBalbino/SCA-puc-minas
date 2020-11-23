package br.com.sga.resources;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sga.model.Comunicacao;
import br.com.sga.model.Pessoa;
import br.com.sga.model.PlanoAcao;
import br.com.sga.queue.EmailSender;
import br.com.sga.service.ComunicacaoService;
import br.com.sga.service.PlanoAcaoService;

@RestController
@RequestMapping("/evacuacao")
public class EvacuacaoResource {

	@Autowired
	private ComunicacaoService service;
	
	@Autowired
	private PlanoAcaoService planoAcaoService;

	@Autowired
	private EmailSender senderQueue;

	
	/**
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> evacuarBarragem(@Valid @RequestBody PlanoAcao planoAcao) {
		
		Comunicacao comunicacao = new Comunicacao();
		comunicacao.setDataInclusao(new Date(System.currentTimeMillis()));
		comunicacao.setPlanoAcao(planoAcao);
		comunicacao = service.save(comunicacao);
		
		PlanoAcao planoAcaoTemp = planoAcaoService.findById(planoAcao.getCodigo());
		for(Pessoa pessoa : planoAcaoTemp.getPessoas()) {
			senderQueue.send(pessoa.getEmail());
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comunicacao.getCodigo())
		.toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
