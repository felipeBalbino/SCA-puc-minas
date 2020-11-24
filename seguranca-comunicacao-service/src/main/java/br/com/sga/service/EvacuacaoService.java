package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.dto.EmailDTO;
import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.model.Comunicacao;
import br.com.sga.model.Pessoa;
import br.com.sga.model.PlanoAcao;
import br.com.sga.queue.EmailSender;
import br.com.sga.repository.ComunicacaoRepository;
import br.com.sga.repository.PlanoAcaoRepository;
import br.com.sga.service.exception.ServiceException;

@Service
public class EvacuacaoService {

	@Autowired
	private ComunicacaoRepository comunicacaoRepository;

	@Autowired
	private EmailSender senderQueue;
	
	@Autowired
	private PlanoAcaoRepository planoAcaoRepository;


	/**
	 * @param comunicacao
	 */
	public Comunicacao evacuarBarragem(ComunicacaoDTO comunicacaoDTO) {
		PlanoAcao planoAcaoTemp = planoAcaoRepository.findById(comunicacaoDTO.getCodigo()).get();

		Comunicacao comunicacao = new Comunicacao();
		comunicacao.setDataInclusao(new Date(System.currentTimeMillis()));
		comunicacao.setPlanoAcao(planoAcaoTemp);
		comunicacao = comunicacaoRepository.save(comunicacao);

		for (Pessoa pessoa : planoAcaoTemp.getPessoas()) {
			EmailDTO emailDTO = new EmailDTO();
			emailDTO.setText(planoAcaoTemp.getMensagemAlerta());
			emailDTO.setSubject(planoAcaoTemp.getDescricao());
			emailDTO.setEmail(pessoa.getEmail());
			emailDTO.setNome(pessoa.getNomeCompleto());
			senderQueue.send(emailDTO);
		}
		return comunicacao;
	}

}
