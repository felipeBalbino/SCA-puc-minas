package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Fabricante;
import br.com.sga.repository.FabricanteRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	/**
	 * @return
	 */
	public List<Fabricante> findAll() {
		return fabricanteRepository.findAll();
	}	
	
	/**
	 * @param fabricante
	 * @return
	 */
	public Fabricante save(Fabricante fabricante) {
		if(fabricante.getCodigo() != null) {
			Optional<Fabricante> a = fabricanteRepository.findById(fabricante.getCodigo());
			
			if(a.isPresent()) {
				throw new ServiceException("Fabricante já existe na base.");
			}
		}
		fabricante.setDataInclusao(new Date(System.currentTimeMillis()));
		return fabricanteRepository.save(fabricante);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Fabricante findById(Long id) {
		Optional<Fabricante> fabricante = fabricanteRepository.findById(id);
		
		if(fabricante.isPresent()) {
			return fabricante.get();
		}
		throw new ServiceException("Fabricante não encontrado.");
	}
	
	/**
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			fabricanteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Fabricante não encontrado.");
		}
	}
	
	/**
	 * @param 
	 */
	public void update(Fabricante fabricante) {
		findById(fabricante.getCodigo());
		fabricante.setDataInclusao(new Date(System.currentTimeMillis()));
		fabricanteRepository.save(fabricante);
	}
}
