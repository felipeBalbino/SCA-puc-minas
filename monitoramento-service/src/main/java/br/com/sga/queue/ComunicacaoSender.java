package br.com.sga.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sga.dto.ComunicacaoDTO;

/**
 * @author sga
 *
 */
@Component
public class ComunicacaoSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	/**
	 * @param order
	 */
	public void send(ComunicacaoDTO comunicacaoDTO) {
		rabbitTemplate.convertAndSend(this.queue.getName(), comunicacaoDTO);
	}
}