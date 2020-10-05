package br.com.sga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	public List<Person> findByDam(Long id);

}
