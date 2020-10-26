package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Barragem;
import br.com.sga.model.Inspecao;

/**
 * @author sga
 *
 */
public interface InspecaoRepository extends JpaRepository<Inspecao, Long> {

	/**
	 * @param id	
	 * @return
	 */
	public List<Inspecao> findByBarragem_Id(Long id);

	/**
	 * @param barragem
	 * @return
	 */
	public List<Inspecao> findByBarragem(Barragem barragem);

}
