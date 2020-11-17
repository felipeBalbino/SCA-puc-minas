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
public class AtivosService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	
	
	/**
	 * @return
	 */
	public List<Ativo> findAll() {
		return ativoRepository.findAll();
	}	
	
	/**
	 * @param ativo
	 * @return
	 */
	public Ativo save(Ativo ativo) {
		if(ativo.getCodigo() != null) {
			Optional<Ativo> a = ativoRepository.findById(ativo.getCodigo());
			
			if(a.isPresent()) {
				throw new ServiceException("Ativo já existe na base.");
			}
		}
		ativo = ativoRepository.save(ativo);

		return ativo;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Ativo findById(Long id) {
		Optional<Ativo> ativo = ativoRepository.findById(id);
		
		if(ativo.isPresent()) {
			return ativo.get();
		}
		throw new ServiceException("Ativo não encontrado.");
	}
	
	/**
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			ativoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Ativo não encontrado.");
		}
	}
	
	/**
	 * @param 
	 */
	public void update(Ativo ativo) {
		findById(ativo.getCodigo());
		ativoRepository.save(ativo);
	}
}
