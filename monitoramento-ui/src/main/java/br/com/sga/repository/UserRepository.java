package br.com.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}