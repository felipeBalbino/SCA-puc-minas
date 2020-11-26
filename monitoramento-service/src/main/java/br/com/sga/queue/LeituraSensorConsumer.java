package br.com.sga.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sga.repository.SensorRepository;
import br.com.sga.service.LeituraSensorService;

@Component
public class LeituraSensorConsumer {
	
	@Autowired
	private LeituraSensorService leituraSensorService;

	Logger logger = LoggerFactory.getLogger(LeituraSensorConsumer.class);

	@RabbitListener(queues = { "${queue.leiturasensor.name}" })
	public void receive(@Payload String codigoLeitura) {
		
		System.out.println("Order: " + codigoLeitura);
		leituraSensorService.gerarLeituraTodosSensores();

	}
}