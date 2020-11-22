package br.com.sga.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Comunicacao;

public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {
	
	/**
	 * @param barragem
	 * @return
	 */
	public Comunicacao findByPlanoAcao_CodigoBarragemOrderByDataInclusaoDesc(Long id);

}
			