package br.com.sga.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sga.dto.PlanoAcaoDTO;

/**
 * @author sga
 *
 */
@Component
public class BarragemSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	/**
	 * @param order
	 */
	public void send(PlanoAcaoDTO planoAcaoDTO) {
		rabbitTemplate.convertAndSend(this.queue.getName(), planoAcaoDTO);
	}
}