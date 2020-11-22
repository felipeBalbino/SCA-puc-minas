package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.DanoPotencial;
import br.com.sga.repository.DanoPotencialRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class DanoPotencialService {

	@Autowired
	private DanoPotencialRepository danoPotencialRepository;

	/**
	 * @return
	 */
	public List<DanoPotencial> findAll() {
		return danoPotencialRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public DanoPotencial save(DanoPotencial danoPotencial) {
		danoPotencial.setDataInclusao(new Date(System.currentTimeMillis()));
		return danoPotencialRepository.save(danoPotencial);
	}

	/**
	 * @param id
	 * @return
	 */
	public DanoPotencial findById(Long id) {
		Optional<DanoPotencial> s = danoPotencialRepository.findById(id);

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
			danoPotencialRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Requisição não encontrada.");
		}
	}

	/**
	 * @param DanoPotencial
	 */
	public void update(DanoPotencial danoPotencial) {
		findById(danoPotencial.getCodigo());
		danoPotencialRepository.save(danoPotencial);
	}
}
