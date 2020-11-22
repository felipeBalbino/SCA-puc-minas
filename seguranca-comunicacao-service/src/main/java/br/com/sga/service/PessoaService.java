package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Pessoa;
import br.com.sga.repository.PessoaRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	/**
	 * @return
	 */
	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	/**
	 * @param pessoa
	 * @return
	 */
	public Pessoa save(Pessoa pessoa) {
		if (pessoa.getCodigo() != null) {
			Optional<Pessoa> a = repository.findById(pessoa.getCodigo());

			if (a.isPresent()) {
				throw new ServiceException("already exist.");
			}
		}
		pessoa.setDataInclusao(new Date(System.currentTimeMillis()));
		return repository.save(pessoa);
	}

	/**
	 * @param id
	 * @return
	 */
	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent()) {
			return pessoa.get();
		}
		throw new ServiceException("can not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("can not be found");
		}
	}

	/**
	 * @param pessoa
	 */
	public void update(Pessoa pessoa) {
		findById(pessoa.getCodigo());
		repository.save(pessoa);
	}
}
