package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.LeituraSensor;
import br.com.sga.model.Sensor;
import br.com.sga.repository.LeituraSensorRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class LeituraSensorService {

	@Autowired
	private LeituraSensorRepository leituraSensorRepository;

	
	/**
	 * @return
	 */
	public List<LeituraSensor> findAll() {
		return leituraSensorRepository.findAll();
	}

	/**
	 * @param leituraSensor
	 * @return
	 */
	public LeituraSensor save(LeituraSensor leituraSensor) {
		return leituraSensorRepository.save(leituraSensor);
	}

	/**
	 * @param id
	 * @return
	 */
	public LeituraSensor findById(Long id) {
		Optional<LeituraSensor> leituraSensor = leituraSensorRepository.findById(id);

		if (leituraSensor.isPresent()) {
			return leituraSensor.get();
		}
		throw new ServiceException("The LeituraSensor could not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			leituraSensorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The LeituraSensor could not be found.");
		}
	}


	/**
	 * @param leituraSensor	
	 */
	public void update(LeituraSensor leituraSensor) {
		findById(leituraSensor.getCodigo());
		leituraSensorRepository.save(leituraSensor);
	}
	
	/**
	 * @return
	 */
	public List<LeituraSensor> findBySensor(Long id) {
		Sensor sensor = new Sensor();
		sensor.setCodigo(id);
		return leituraSensorRepository.findBySensor(sensor);
	}
	
}
