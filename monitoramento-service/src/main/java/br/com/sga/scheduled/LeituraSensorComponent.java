package br.com.sga.scheduled;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.model.LeituraSensor;
import br.com.sga.model.Sensor;
import br.com.sga.queue.ComunicacaoSender;
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
	
	@Autowired
	private ComunicacaoSender senderQueue;
	

	@Scheduled(fixedRate = 9000)
	public void mockLeituraSensor() {
		logger.info("Criação de leitura de sensor para todos os sensores cadastrados.");
		
		List<Sensor> lista = sensorService.findAll();
		for(Sensor sensor:lista) {
			LeituraSensor leituraSensor = criarLeiturasSensores(sensor);
			leituraSensorService.save(leituraSensor);
			if(leituraSensor.getLeitura() >= sensor.getTipoSensor().getMaxLeitura() || leituraSensor.getLeitura() <= sensor.getTipoSensor().getMinLeitura()) {
				PlanoAcao
				
				ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO();
				comunicacaoDTO.setCodigo(codigo);
				comunicacaoDTO.setCodigoAtivo(sensor.getCodigoAtivo());
				comunicacaoDTO.setGrauRisco(grauRisco);
				senderQueue.send(comunicacaoDTO);
			}
		}
		
	}
	
	private LeituraSensor criarLeiturasSensores(Sensor sensor) {

		LeituraSensor leituraSensor = new LeituraSensor();
		leituraSensor.setSensor(sensor);
		leituraSensor.setDataInclusao(new Date(System.currentTimeMillis()));
		
		Random r = new Random();
		double randomValue = r.nextInt((100 - 1) + 1) + 1;
		
		leituraSensor.setLeitura(randomValue);
		
		return leituraSensor;
	}
}
