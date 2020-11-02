package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.CategoriaRisco;
import br.com.sga.model.DanoPotencial;
import br.com.sga.model.TipoMetodo;

/**
 * @author sga
 *
 */
public interface TipoMetodoRepository extends JpaRepository<TipoMetodo, Long> {

	/**
	 * @param name
	 * @return
	 */
	public List<TipoMetodo> findByNomeContaining(String name);
}
