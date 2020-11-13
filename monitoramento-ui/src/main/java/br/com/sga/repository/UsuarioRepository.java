package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByNome(String nome);
}