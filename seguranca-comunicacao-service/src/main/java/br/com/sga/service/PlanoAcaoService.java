package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.PlanoAcao;
import br.com.sga.repository.PlanoAcaoRepository;
import br.com.sga.service.exception.ServiceException;

/**	
 * @author sga
 *	
 */
@Service
public class PlanoAcaoService {

	@Autowired
	private PlanoAcaoRepository repository;

	/**
	 * @return
	 */
	public List<PlanoAcao> findAll() {
		return repository.findAll();
	}

	/**
	 * @param planoAcao
	 * @return
	 */
	public PlanoAcao save(PlanoAcao planoAcao) {
		if (planoAcao.getCodigo() != null) {
			Optional<PlanoAcao> a = repository.findById(planoAcao.getCodigo());

			if (a.isPresent()) {
				throw new ServiceException("already exist.");
			}
		}
		planoAcao.setDataInclusao(new Date(System.currentTimeMillis()));
		return repository.save(planoAcao);
	}

	/**
	 * @param id
	 * @return
	 */
	public PlanoAcao findById(Long id) {
		Optional<PlanoAcao> planoAcao = repository.findById(id);

		if (planoAcao.isPresent()) {
			return planoAcao.get();
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
	 * @param planoAcao
	 */
	public void update(PlanoAcao planoAcao) {
		findById(planoAcao.getCodigo());
		repository.save(planoAcao);
	}
}
