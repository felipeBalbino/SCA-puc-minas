package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Barragem;
import br.com.sga.model.Sensor;
import br.com.sga.repository.SensorRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class SensorService {

	@Autowired
	private SensorRepository sensorRepository;

	/**
	 * @return
	 */
	public List<Sensor> findAll() {
		return sensorRepository.findAll();
	}

	/**
	 * @param sensor
	 * @return
	 */
	public Sensor save(Sensor sensor) {
		return sensorRepository.save(sensor);
	}

	/**
	 * @param id
	 * @return
	 */
	public Sensor findById(Long id) {
		Optional<Sensor> sensor = sensorRepository.findById(id);

		if (sensor.isPresent()) {
			return sensor.get();
		}
		throw new ServiceException("The Sensor could not be found.");
	}
	
	/**
	 * @param id
	 * @return
	 */
	public List<Sensor> findByBarragem(Long codigo) {
		Barragem barragem = new Barragem();
		barragem.setCodigo(codigo);
		return sensorRepository.findByBarragem(barragem);
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			sensorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The Sensor could not be found.");
		}
	}


	/**
	 * @param sensor	
	 */
	public void update(Sensor sensor) {
		findById(sensor.getCodigo());
		sensorRepository.save(sensor);
	}
}
