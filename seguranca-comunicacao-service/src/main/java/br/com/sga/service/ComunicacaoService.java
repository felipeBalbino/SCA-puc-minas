package br.com.sga.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sga.model.Acao;
import br.com.sga.model.Comunicacao;
import br.com.sga.model.Pessoa;
import br.com.sga.queue.EmailSender;
import br.com.sga.repository.ComunicacaoRepository;

@Service
public class ComunicacaoService {

	@Autowired
	private EmailSender senderQueue;

	@Autowired
	private ComunicacaoRepository repository;

	/**
	 * @return
	 */
	public List<Comunicacao> findAll() {
		return repository.findAll();
	}

	/**
	 * @param communication
	 * @return
	 */
	public Comunicacao save(Acao acao) {
		Comunicacao comunicacao = new Comunicacao();
		comunicacao.setAcao(acao);
		for (Pessoa p : acao.getPessoas()) {
			senderQueue.send(p.getNomeCompleto() + " <" + p.getEmail() + ">");
		}
		comunicacao.setDataInclusao(new Date());
		return repository.save(comunicacao);
	}
}
