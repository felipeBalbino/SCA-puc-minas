package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Dam;

/**
 * @author sga
 *
 */
public interface DamRepository extends JpaRepository<Dam, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<Dam> findByNameContaining(String name);
}
