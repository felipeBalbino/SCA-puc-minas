package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}