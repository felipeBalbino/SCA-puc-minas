package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {

}
