package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.CategoriaRisco;
import br.com.sga.repository.CategoriaRiscoRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class CategoriaRiscoService {

	@Autowired
	private CategoriaRiscoRepository categoriaRiscoRepository;

	/**
	 * @return
	 */
	public List<CategoriaRisco> findAll() {
		return categoriaRiscoRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public CategoriaRisco save(CategoriaRisco categoriaRisco) {
		categoriaRisco.setDataInclusao(new Date(System.currentTimeMillis()));
		return categoriaRiscoRepository.save(categoriaRisco);
	}

	/**
	 * @param id
	 * @return
	 */
	public CategoriaRisco findById(Long id) {
		Optional<CategoriaRisco> s = categoriaRiscoRepository.findById(id);

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
			categoriaRiscoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Requisição não encontrada.");
		}
	}



	/**
	 * @param CategoriaRisco
	 */
	public void update(CategoriaRisco categoriaRisco) {
		findById(categoriaRisco.getCodigo());
		categoriaRiscoRepository.save(categoriaRisco);
	}
}
