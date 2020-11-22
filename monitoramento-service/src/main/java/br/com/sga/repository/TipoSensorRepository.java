package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.TipoSensor;

/**
 * @author sga
 *
 */
public interface TipoSensorRepository extends JpaRepository<TipoSensor, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<TipoSensor> findByNomeContaining(String name);
}
