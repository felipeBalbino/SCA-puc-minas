package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
	public List<Manutencao> findByAtivo_Codigo(Long id	);

}
