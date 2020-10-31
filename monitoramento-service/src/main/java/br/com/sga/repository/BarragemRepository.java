package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;

/**
 * @author sga
 *
 */
public interface BarragemRepository extends JpaRepository<Barragem, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<Barragem> findByNomeContaining(String name);
}
