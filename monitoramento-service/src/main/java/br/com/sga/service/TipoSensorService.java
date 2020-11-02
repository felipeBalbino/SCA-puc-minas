package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.TipoSensor;
import br.com.sga.repository.TipoSensorRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class TipoSensorService {

	@Autowired
	private TipoSensorRepository tipoSensorRepository;

	/**
	 * @return
	 */
	public List<TipoSensor> findAll() {
		return tipoSensorRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public TipoSensor save(TipoSensor s) {
		return tipoSensorRepository.save(s);
	}

	/**
	 * @param id
	 * @return
	 */
	public TipoSensor findById(Long id) {
		Optional<TipoSensor> s = tipoSensorRepository.findById(id);

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
			tipoSensorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Requisição não encontrada.");
		}
	}

	/**
	 * @param TipoSensor
	 */
	public void update(TipoSensor tipoSensor) {
		findById(tipoSensor.getCodigo());
		tipoSensorRepository.save(tipoSensor);
	}
}
