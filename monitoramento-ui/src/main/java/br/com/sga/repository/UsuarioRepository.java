package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sga.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNome(String nome);
	
	@Query("SELECT p FROM Usuario p WHERE LOWER(p.nome) = LOWER(:nome) and p.dataInativacao is null ")
	Usuario find(@Param("nome") String nome);
}