package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Inspect;
import br.com.sga.repository.InspectRepository;
import br.com.sga.repository.filter.InspectFilter;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class InspectService {

	@Autowired
	private InspectRepository InspectRepository;

	/**
	 * @return
	 */
	public List<Inspect> findAll() {
		return InspectRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public Inspect save(Inspect s) {
		return InspectRepository.save(s);
	}

	/**
	 * @param id
	 * @return
	 */
	public Inspect findById(Long id) {
		Optional<Inspect> s = InspectRepository.findById(id);

		if (s.isPresent()) {
			return s.get();
		}
		throw new ServiceException("The Request could not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			InspectRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The Request could not be found.");
		}
	}

	/**
	 * @param inspectFilter
	 * @return
	 */
	public List<Inspect> search(InspectFilter inspectFilter) {
		return InspectRepository.findByDam_Id(inspectFilter.getId());

	}

	/**
	 * @param Inspect
	 */
	public void update(Inspect inspect) {
		findById(inspect.getId());
		InspectRepository.save(inspect);
	}
}
