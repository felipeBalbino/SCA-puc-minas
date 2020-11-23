package br.com.sga.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sga.dto.PlanoAcaoDTO;
import br.com.sga.model.PlanoAcao;
import br.com.sga.service.EvacuacaoService;

@Component
public class BarragemConsumer {

	@Autowired
	private EvacuacaoService evacuacaoService;
	
	Logger logger = LoggerFactory.getLogger(BarragemConsumer.class);

	@RabbitListener(queues = { "${queue.evacuacao.name}" })
	public void receive(@Payload PlanoAcaoDTO planoAcaoDTO) {
		System.out.println("Order: " + planoAcaoDTO.toString());
		try {
			evacuacaoService.evacuarBarragem(planoAcaoDTO);
		} catch (Exception e) {
			logger.error("erro enviando evacuacao de barragem", e);
		}
	}
}