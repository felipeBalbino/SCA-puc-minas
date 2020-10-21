package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.TipoAtivo;
import br.com.sga.repository.TipoAtivoRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class TipoAtivoService {

	@Autowired
	private TipoAtivoRepository tipoAtivoRepository;

	/**
	 * @return
	 */
	public List<TipoAtivo> findAll() {
		return tipoAtivoRepository.findAll();
	}	
	
	/**
	 * @param tipoAtivo
	 * @return
	 */
	public TipoAtivo save(TipoAtivo tipoAtivo) {
		if(tipoAtivo.getCodigo() != null) {
			Optional<TipoAtivo> a = tipoAtivoRepository.findById(tipoAtivo.getCodigo());
			
			if(a.isPresent()) {
				throw new ServiceException("TipoAtivo já existe na base.");
			}
		}
		return tipoAtivoRepository.save(tipoAtivo);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public TipoAtivo findById(Long id) {
		Optional<TipoAtivo> tipoAtivo = tipoAtivoRepository.findById(id);
		
		if(tipoAtivo.isPresent()) {
			return tipoAtivo.get();
		}
		throw new ServiceException("TipoAtivo não encontrado.");
	}
	
	/**
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			tipoAtivoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("TipoAtivo não encontrado.");
		}
	}
	
	/**
	 * @param 
	 */
	public void update(TipoAtivo tipoAtivo) {
		findById(tipoAtivo.getCodigo());
		tipoAtivoRepository.save(tipoAtivo);
	}
}
