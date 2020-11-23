package br.com.sga.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Comunicacao;

public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {
	
	/**
	 * @param barragem
	 * @return
	 */
	public List<Comunicacao> findByPlanoAcao_CodigoBarragemOrderByDataInclusaoDesc(Long id);

}
			