package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Dam;
import br.com.sga.repository.DamRepository;
import br.com.sga.repository.filter.DamFilter;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class DamService {

	@Autowired
	private DamRepository damRepository;

	/**
	 * @return
	 */
	public List<Dam> findAll() {
		return damRepository.findAll();
	}

	/**
	 * @param dam
	 * @return
	 */
	public Dam save(Dam dam) {
		return damRepository.save(dam);
	}

	/**
	 * @param id
	 * @return
	 */
	public Dam findById(Long id) {
		Optional<Dam> dam = damRepository.findById(id);

		if (dam.isPresent()) {
			return dam.get();
		}
		throw new ServiceException("The Dam could not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			damRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The Dam could not be found.");
		}
	}

	/**
	 * @param filter
	 * @return
	 */
	public List<Dam> search(DamFilter filter) {
		String description = filter.getDescription() == null ? "%" : filter.getDescription();
		return damRepository.findByNameContaining(description);

	}

	/**
	 * @param dam	
	 */
	public void update(Dam dam) {
		findById(dam.getId());
		damRepository.save(dam);
	}
}
