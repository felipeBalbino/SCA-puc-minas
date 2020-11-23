package br.com.sga.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sga.dto.EmailDTO;
import br.com.sga.email.Send;

 
@Component
public class EmailConsumer {
 
	Logger logger = LoggerFactory.getLogger(EmailConsumer.class);
	
    @RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload EmailDTO emailDTO) {
    	System.out.println("Order: " + emailDTO.toString());
    	try {
			Send.send(emailDTO);
		} catch (Exception e) {
			logger.error("erro enviando email",e);
		}
    }
}