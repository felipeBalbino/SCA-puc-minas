package br.com.sga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sga.model.TipoAtivo;
import br.com.sga.repository.TipoAtivoRepository;

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
}
