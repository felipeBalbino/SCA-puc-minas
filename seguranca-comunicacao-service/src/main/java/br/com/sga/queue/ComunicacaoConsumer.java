package br.com.sga.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.model.PlanoAcao;
import br.com.sga.service.EvacuacaoService;

@Component
public class ComunicacaoConsumer {

	@Autowired
	private EvacuacaoService evacuacaoService;
	
	Logger logger = LoggerFactory.getLogger(ComunicacaoConsumer.class);

	@RabbitListener(queues = { "${queue.comunicacao.name}" })
	public void receive(@Payload ComunicacaoDTO comunicacaoDTO) {
		System.out.println("Order: " + comunicacaoDTO.toString());
		try {
			evacuacaoService.evacuarBarragem(comunicacaoDTO);
		} catch (Exception e) {
			logger.error("erro enviando evacuacao de barragem", e);
		}
	}
}