package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


}
