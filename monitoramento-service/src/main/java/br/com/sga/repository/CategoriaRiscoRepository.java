package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.CategoriaRisco;
import br.com.sga.model.DanoPotencial;

/**
 * @author sga
 *
 */
public interface CategoriaRiscoRepository extends JpaRepository<CategoriaRisco, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<CategoriaRisco> findByNomeContaining(String name);
}
