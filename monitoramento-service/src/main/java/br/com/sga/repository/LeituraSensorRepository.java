package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.LeituraSensor;
import br.com.sga.model.Sensor;

/**
 * @author sga
 *
 */
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<LeituraSensor> findBySensor(Sensor sensor);
}
