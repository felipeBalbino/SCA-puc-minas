package br.com.sga.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sga.dto.EmailDTO;

/**
 * @author sga
 *
 */
@Component
public class EmailSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	/**
	 * @param order
	 */
	public void send(EmailDTO emailDTO) {
		rabbitTemplate.convertAndSend(this.queue.getName(), emailDTO);
	}
}