package br.com.sga.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sga.model.Communication;
import br.com.sga.model.Person;
import br.com.sga.queue.EmailSender;
import br.com.sga.repository.CommunicationRepository;

@Service
public class CommunicationService {	

	@Autowired
	private PersonService service;

	@Autowired
	private EmailSender senderQueue;

	@Autowired
	private CommunicationRepository repository;

	/**
	 * @return
	 */
	public List<Communication> findAll() {
		return repository.findAll();
	}

	/**
	 * @param communication
	 * @return
	 */
	public Communication save(Communication communication) {
		List<Person> lista = service.findByDam(communication.getDam());
		for (Person p : lista) {
			senderQueue.send(p.getName() + " <" + p.getEmail() + ">");
		}
		communication.setDate(new Date());
		return repository.save(communication);
	}
}
