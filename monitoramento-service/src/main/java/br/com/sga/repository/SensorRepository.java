package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.CategoriaRisco;
import br.com.sga.model.DanoPotencial;
import br.com.sga.model.Sensor;
import br.com.sga.model.TipoMetodo;
import br.com.sga.model.TipoSensor;

/**
 * @author sga
 *
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<Sensor> findByCodigoAtivoContaining(Long codigoAtivo);
}
