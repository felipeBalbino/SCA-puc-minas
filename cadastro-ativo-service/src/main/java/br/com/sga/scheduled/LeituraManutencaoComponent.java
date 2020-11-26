package br.com.sga.scheduled;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sga.dto.ComunicacaoDTO;
import br.com.sga.model.Manutencao;
import br.com.sga.model.StatusManutencaoEnum;
import br.com.sga.queue.ComunicacaoSender;
import br.com.sga.service.ManutencaoService;

/**
 * @author sga
 *
 */

@Component 
public class LeituraManutencaoComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(LeituraManutencaoComponent.class);


	@Autowired
	private ManutencaoService manutencaoService;
	
	@Autowired
	private ComunicacaoSender senderQueue;
	

	@Scheduled(fixedRate = 90000)
	public void mockLeituraSensor() {
		logger.info("Criação de leitura de manutencao para todos os sensores cadastrados fora da data de manutencao.");
		
		List<Manutencao> lista = manutencaoService.findAll();
		for(Manutencao manutencao:lista) {
			if(manutencao.getStatusManutencao().getDescricao().equals(StatusManutencaoEnum.ABERTA.getDescricao()) &&
					manutencao.getDataProxManutencao().before(new Date())) {
				ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO();
				comunicacaoDTO.setTipoComunicacao("MANUTENCAO");
				comunicacaoDTO.setCodigoAtivo(manutencao.getAtivo().getCodigo());
				senderQueue.send(comunicacaoDTO);
			}
		}
	}

}
