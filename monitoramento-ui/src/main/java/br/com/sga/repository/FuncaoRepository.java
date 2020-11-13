package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long>{
}