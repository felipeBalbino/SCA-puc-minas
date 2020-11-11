package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Acao;
import br.com.sga.model.PlanoAcao;

public interface PlanoAcaoRepository extends JpaRepository<PlanoAcao, Long> {


}
