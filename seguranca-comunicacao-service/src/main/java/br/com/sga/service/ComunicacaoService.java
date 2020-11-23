package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Comunicacao;
import br.com.sga.queue.EmailSender;
import br.com.sga.repository.ComunicacaoRepository;
import br.com.sga.service.exception.ServiceException;

@Service
public class ComunicacaoService {



	@Autowired
	private ComunicacaoRepository repository;

	/**
	 * @return
	 */
	public List<Comunicacao> findAll() {
		return repository.findAll();
	}
	
	/**
	 * @param pessoa
	 * @return
	 */
	public Comunicacao save(Comunicacao comunicacao) {
		if (comunicacao.getCodigo() != null) {
			Optional<Comunicacao> a = repository.findById(comunicacao.getCodigo());

			if (a.isPresent()) {
				throw new ServiceException("already exist.");
			}
		}
		comunicacao.setDataInclusao(new Date(System.currentTimeMillis()));
		return repository.save(comunicacao);
	}

//	/**
//	 * @param communication
//	 * @return
//	 */
//	public Comunicacao save(PlanoAcao planoAcao) {
//		Comunicacao comunicacao = new Comunicacao();
//		comunicacao.setPlanoAcao(planoAcao);
//		for (Pessoa p : planoAcao.getPessoas()) {
//			senderQueue.send(p.getNomeCompleto() + " <" + p.getEmail() + ">");
//		}
//		comunicacao.setDataInclusao(new Date(System.currentTimeMillis()));
//		return repository.save(comunicacao);
//	}
	
	/**	
	 * @param id
	 * @return
	 */
	public Comunicacao findById(Long id) {
		Optional<Comunicacao> comunicacao = repository.findById(id);

		if (comunicacao.isPresent()) {
			return comunicacao.get();
		}
		throw new ServiceException("can not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("can not be found");
		}
	}

	/**
	 * @param comunicacao
	 */
	public void update(Comunicacao comunicacao) {
		findById(comunicacao.getCodigo());
		comunicacao.setDataInclusao(new Date(System.currentTimeMillis()));
		repository.save(comunicacao);
	}
	

	/**
	 * @param id
	 * @return
	 */
	public Comunicacao findByUltimaComunicacaoByBarragem(Long id) {
		List<Comunicacao> list = repository.findByPlanoAcao_CodigoBarragemOrderByDataInclusaoDesc(id);

		return ((list != null && !list.isEmpty())?list.get(0):null);

	}
}
