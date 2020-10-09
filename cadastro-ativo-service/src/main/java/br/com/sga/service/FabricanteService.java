package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Ativo;
import br.com.sga.repository.AtivoRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class FabricanteService {

	@Autowired
	private AtivoRepository ativoRepository;

	/**
	 * @return
	 */
	public List<Ativo> findAll() {
		return ativoRepository.findAll();
	}
}
