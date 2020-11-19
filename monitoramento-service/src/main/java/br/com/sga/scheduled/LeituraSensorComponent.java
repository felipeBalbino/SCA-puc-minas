package br.com.sga.scheduled;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sga.model.LeituraSensor;
import br.com.sga.model.Sensor;
import br.com.sga.service.LeituraSensorService;
import br.com.sga.service.SensorService;

/**
 * @author sga
 *
 */

@Component 
public class LeituraSensorComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(LeituraSensorComponent.class);

	@Autowired
	private LeituraSensorService leituraSensorService;

	@Autowired
	private SensorService sensorService;
	

	@Scheduled(fixedRate = 9000)
	public void mockLeituraSensor() {
		logger.info("Criação de leitura de sensor para todos os sensores cadastrados.");
		
		List<Sensor> lista = sensorService.findAll();
		for(Sensor sensor:lista) {
			leituraSensorService.save(criarLeiturasSensores(sensor));
		}
		
	}
	
	private LeituraSensor criarLeiturasSensores(Sensor sensor) {

		LeituraSensor leituraSensor = new LeituraSensor();
		leituraSensor.setSensor(sensor);
		leituraSensor.setDataInclusao(new Date(System.currentTimeMillis()));
		
		Random r = new Random();
		double randomValue = 2 + (5 - 2) * r.nextDouble();
		
		leituraSensor.setLeitura(randomValue);
		
		return leituraSensor;
	}
}
