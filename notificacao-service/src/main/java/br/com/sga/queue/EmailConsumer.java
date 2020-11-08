package br.com.sga.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.sga.email.Send;

 
@Component
public class EmailConsumer {
 
	Logger logger = LoggerFactory.getLogger(EmailConsumer.class);
	
    @RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload String email) {
    	System.out.println("Order: " + email);
    	try {
			Send.send(email, "Alerta de Evacuação", "Alerta de Evacuação - Favor procurar um local seguro.");
		} catch (Exception e) {
			logger.error("erro enviando email",e);
		}
    }
}