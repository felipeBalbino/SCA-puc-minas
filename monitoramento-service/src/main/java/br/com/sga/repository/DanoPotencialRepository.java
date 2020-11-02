package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.DanoPotencial;

/**
 * @author sga
 *
 */
public interface DanoPotencialRepository extends JpaRepository<DanoPotencial, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<DanoPotencial> findByNomeContaining(String name);
}
