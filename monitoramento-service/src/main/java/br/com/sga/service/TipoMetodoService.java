package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.TipoMetodo;
import br.com.sga.repository.TipoMetodoRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class TipoMetodoService {

	@Autowired
	private TipoMetodoRepository tipoMetodoRepository;

	/**
	 * @return
	 */
	public List<TipoMetodo> findAll() {
		return tipoMetodoRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public TipoMetodo save(TipoMetodo s) {
		return tipoMetodoRepository.save(s);
	}

	/**
	 * @param id
	 * @return
	 */
	public TipoMetodo findById(Long id) {
		Optional<TipoMetodo> s = tipoMetodoRepository.findById(id);

		if (s.isPresent()) {
			return s.get();
		}
		throw new ServiceException("Requisição não encontrada.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			tipoMetodoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Requisição não encontrada.");
		}
	}

	/**
	 * @param TipoMetodo
	 */
	public void update(TipoMetodo tipoMetodo) {
		findById(tipoMetodo.getCodigo());
		tipoMetodoRepository.save(tipoMetodo);
	}
}
