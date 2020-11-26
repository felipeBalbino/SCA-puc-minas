package br.com.sga.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sga.service.LeituraSensorService;

/**
 * @author sga
 *
 */

@Component 
public class LeituraSensorComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(LeituraSensorComponent.class);

	@Autowired
	private LeituraSensorService leituraSensorService;
	

	@Scheduled(fixedRate = 20000)
	public void mockLeituraSensor() {
		
		leituraSensorService.gerarLeituraTodosSensores();
		logger.info("Criação de leitura de sensor para todos os sensores cadastrados.");
		
	}

}
