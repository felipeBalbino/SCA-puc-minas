package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Manutencao;
import br.com.sga.model.StatusManutencaoEnum;
import br.com.sga.repository.ManutencaoRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author fjbalbino
 *
 */
@Service
public class ManutencaoService {

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	/**
	 * @return
	 */
	public List<Manutencao> findAll() {
		return manutencaoRepository.findAll();
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Manutencao> findByAtivo_Codigo(Long id) {
		return manutencaoRepository.findByAtivo_Codigo(id);
	}

	/**
	 * @param s
	 * @return
	 */
	public Manutencao save(Manutencao s) {
		s.setDataManutencao(new Date(System.currentTimeMillis()));
		s.setStatusManutencao(StatusManutencaoEnum.ABERTA);
		return manutencaoRepository.save(s);
	}

	/**
	 * @param id
	 * @return
	 */
	public Manutencao findById(Long id) {
		Optional<Manutencao> s = manutencaoRepository.findById(id);

		if (s.isPresent()) {
			return s.get();
		}
		throw new ServiceException("O Manutencao não pôde ser encontrado.");
	}

	/**
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			manutencaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("O manutencao não pôde ser encontrado.");
		}
	}

	/**
	 * @param manutencao
	 */
	public void update(Manutencao manutencao) {
		findById(manutencao.getCodigo());
		manutencao.setDataInclusao(new Date(System.currentTimeMillis()));
		manutencaoRepository.save(manutencao);
	}
}
