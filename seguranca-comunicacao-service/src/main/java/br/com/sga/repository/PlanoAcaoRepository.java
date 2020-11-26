package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.PlanoAcao;

public interface PlanoAcaoRepository extends JpaRepository<PlanoAcao, Long> {

	
	/**
	 * @param barragem
	 * @return
	 */
	public List<PlanoAcao> findByCodigoAtivoOrderByDataInclusaoDesc(Long id);

}
