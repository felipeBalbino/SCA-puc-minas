package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.Sensor;

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
	
	/**
	 * @param barragem
	 * @return
	 */
	public List<Sensor> findByBarragem(Barragem barragem);
}
