package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Inspecao;
import br.com.sga.repository.InspecaoRepository;
import br.com.sga.repository.filter.InspecaoFilter;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class InspecaoService {

	@Autowired
	private InspecaoRepository InspecaoRepository;

	/**
	 * @return
	 */
	public List<Inspecao> findAll() {
		return InspecaoRepository.findAll();
	}

	/**
	 * @param s
	 * @return
	 */
	public Inspecao save(Inspecao s) {
		return InspecaoRepository.save(s);
	}

	/**
	 * @param id
	 * @return
	 */
	public Inspecao findById(Long id) {
		Optional<Inspecao> s = InspecaoRepository.findById(id);

		if (s.isPresent()) {
			return s.get();
		}
		throw new ServiceException("Requisição não encontrada.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			InspecaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("Requisição não encontrada.");
		}
	}

	/**
	 * @param inspecaoFilter
	 * @return
	 */
	public List<Inspecao> search(InspecaoFilter inspecaoFilter) {
		return InspecaoRepository.findByBarragem_Codigo(inspecaoFilter.getId());

	}

	/**
	 * @param Inspecao
	 */
	public void update(Inspecao inspecao) {
		findById(inspecao.getCodigo());
		InspecaoRepository.save(inspecao);
	}
}
