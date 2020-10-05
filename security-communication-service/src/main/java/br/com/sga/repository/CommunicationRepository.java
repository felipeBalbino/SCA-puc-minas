package br.com.sga.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sga.model.Communication;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {

}
		