package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Barragem;
import br.com.sga.repository.BarragemRepository;
import br.com.sga.repository.filter.BarragemFilter;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class BarragemService {

	@Autowired
	private BarragemRepository barragemRepository;

	/**
	 * @return
	 */
	public List<Barragem> findAll() {
		return barragemRepository.findAll();
	}

	/**
	 * @param barragem
	 * @return
	 */
	public Barragem save(Barragem barragem) {
		return barragemRepository.save(barragem);
	}

	/**
	 * @param id
	 * @return
	 */
	public Barragem findById(Long id) {
		Optional<Barragem> barragem = barragemRepository.findById(id);

		if (barragem.isPresent()) {
			return barragem.get();
		}
		throw new ServiceException("The Barragem could not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			barragemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The Barragem could not be found.");
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	public List<Barragem> search(BarragemFilter filter) {
		String descricao = filter.getDescricao() == null ? "%" : filter.getDescricao();
		return barragemRepository.findByNameContaining(descricao);

	}

	/**
	 * @param barragem	
	 */
	public void update(Barragem barragem) {
		findById(barragem.getId());
		barragemRepository.save(barragem);
	}
}
