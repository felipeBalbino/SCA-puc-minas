package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Acao;
import br.com.sga.repository.AcaoRepository;
import br.com.sga.service.exception.ServiceException;

/**	
 * @author sga
 *	
 */
@Service
public class AcaoService {

	@Autowired
	private AcaoRepository repository;

	/**
	 * @return
	 */
	public List<Acao> findAll() {
		return repository.findAll();
	}

	/**
	 * @param acao
	 * @return
	 */
	public Acao save(Acao acao) {
		if (acao.getCodigo() != null) {
			Optional<Acao> a = repository.findById(acao.getCodigo());

			if (a.isPresent()) {
				throw new ServiceException("already exist.");
			}
		}
		return repository.save(acao);
	}

	/**
	 * @param id
	 * @return
	 */
	public Acao findById(Long id) {
		Optional<Acao> acao = repository.findById(id);

		if (acao.isPresent()) {
			return acao.get();
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
	 * @param acao
	 */
	public void update(Acao acao) {
		findById(acao.getCodigo());
		repository.save(acao);
	}
}
