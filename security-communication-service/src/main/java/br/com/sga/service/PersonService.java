package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.model.Person;
import br.com.sga.repository.PersonRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	/**
	 * @return
	 */
	public List<Person> findAll() {
		return repository.findAll();
	}

	/**
	 * @param person
	 * @return
	 */
	public Person save(Person person) {
		if (person.getId() != null) {
			Optional<Person> a = repository.findById(person.getId());

			if (a.isPresent()) {
				throw new ServiceException("already exist.");
			}
		}
		return repository.save(person);
	}

	/**
	 * @param id
	 * @return
	 */
	public Person findById(Long id) {
		Optional<Person> person = repository.findById(id);

		if (person.isPresent()) {
			return person.get();
		}
		throw new ServiceException("can not be found.");
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Person> findByDam(Long id) {
		List<Person> list = repository.findByDam(id);
		return list;
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
	 * @param person
	 */
	public void update(Person person) {
		findById(person.getId());
		repository.save(person);
	}
}
