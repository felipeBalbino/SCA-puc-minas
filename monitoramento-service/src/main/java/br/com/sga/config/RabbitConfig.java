package br.com.sga.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

	
	@Value("${queue.evacuacao.name}")
    private String evacuacaoQueue;
	
	@Bean
    public Queue queue() {
        return new Queue(evacuacaoQueue, true);
    }
}
