package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Dam;
import br.com.sga.model.Inspect;

/**
 * @author sga
 *
 */
public interface InspectRepository extends JpaRepository<Inspect, Long> {

	/**
	 * @param id	
	 * @return
	 */
	public List<Inspect> findByDam_Id(Long id);

	/**
	 * @param dam
	 * @return
	 */
	public List<Inspect> findByDam(Dam dam);

}
