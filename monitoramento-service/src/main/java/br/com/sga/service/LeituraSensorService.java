package br.com.sga.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.model.LeituraSensor;
import br.com.sga.model.Sensor;
import br.com.sga.queue.ComunicacaoSender;
import br.com.sga.repository.LeituraSensorRepository;
import br.com.sga.repository.SensorRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class LeituraSensorService {

	@Autowired
	private LeituraSensorRepository leituraSensorRepository;

	
	@Autowired
	private SensorRepository sensorRepository;
	
	@Autowired
	private ComunicacaoSender senderQueue;
	
	/**
	 * @return
	 */
	public List<LeituraSensor> findAll() {
		return leituraSensorRepository.findAll();
	}

	/**
	 * @param leituraSensor
	 * @return
	 */
	public LeituraSensor save(LeituraSensor leituraSensor) {
		leituraSensor.setDataInclusao(new Date(System.currentTimeMillis()));
		return leituraSensorRepository.save(leituraSensor);
	}

	/**
	 * @param id
	 * @return
	 */
	public LeituraSensor findById(Long id) {
		Optional<LeituraSensor> leituraSensor = leituraSensorRepository.findById(id);

		if (leituraSensor.isPresent()) {
			return leituraSensor.get();
		}
		throw new ServiceException("The LeituraSensor could not be found.");
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			leituraSensorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The LeituraSensor could not be found.");
		}
	}


	/**
	 * @param leituraSensor	
	 */
	public void update(LeituraSensor leituraSensor) {
		findById(leituraSensor.getCodigo());
		leituraSensor.setDataInclusao(new Date(System.currentTimeMillis()));
		leituraSensorRepository.save(leituraSensor);
	}
	
	/**
	 * @return
	 */
	public List<LeituraSensor> findBySensor(Long id) {
		Sensor sensor = new Sensor();
		sensor.setCodigo(id);
		return leituraSensorRepository.findBySensor(sensor);
	}
	
	public void gerarLeituraTodosSensores() {

		List<Sensor> lista = sensorRepository.findAll();
		for(Sensor sensor:lista) {
			LeituraSensor leituraSensor = criarLeiturasSensores(sensor);
			leituraSensorRepository.save(leituraSensor);
			if(leituraSensor.getLeitura() > sensor.getTipoSensor().getMaxLeitura() || leituraSensor.getLeitura() < sensor.getTipoSensor().getMinLeitura()) {
				ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO();
				comunicacaoDTO.setTipoComunicacao("SENSOR");
				comunicacaoDTO.setCodigoAtivo(sensor.getCodigoAtivo());
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
