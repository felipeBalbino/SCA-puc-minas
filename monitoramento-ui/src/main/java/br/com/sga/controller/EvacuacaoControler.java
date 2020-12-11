package br.com.sga.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.binder.LenientDateParser;
import br.com.sga.client.EvacuacaoClient;
import br.com.sga.client.InspecaoClient;
import br.com.sga.dto.GrauRiscoEnum;
import br.com.sga.dto.Inspecao;
import br.com.sga.dto.ComunicacaoDTO;


@Controller
@RequestMapping({"/evacuacao"})
public class EvacuacaoControler {
	
	@RequestMapping
	public String search() {
		return "evacuacao";	
	}
	
	@Value("${zuul.ws.gateway}")	
    private String gateway;
	
	@Value("${zuul.ws.user}")
    private String user;
	
	@Value("${zuul.ws.password}")
    private String password;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//2020-11-22T15:51
	    dateTimeFormat.setLenient(false);
	     
		binder.registerCustomEditor(Date.class,new LenientDateParser(dateTimeFormat, 
			     true));
	}
	

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public String evacuacao(@PathVariable Long codigo, Authentication authentication, RedirectAttributes attr, HttpServletRequest request) {
        
		InspecaoClient inspecaoClient = new InspecaoClient(gateway, user, password);
		Inspecao ultimainspecao = inspecaoClient.ultimaInspecaoByIdBarragem(codigo);
		
		ComunicacaoDTO comunicacaoDTO = new ComunicacaoDTO();
		comunicacaoDTO.setCodigo(codigo);
		comunicacaoDTO.setCodigoAtivo(codigo);
		if(ultimainspecao.getCategoriaRisco().getCodigo().equals(1L)) {
			comunicacaoDTO.setGrauRisco(GrauRiscoEnum.BAIXO.getDescricao());
		}else if(ultimainspecao.getCategoriaRisco().getCodigo().equals(2L)) {
			comunicacaoDTO.setGrauRisco(GrauRiscoEnum.MEDIO.getDescricao());
		}else if(ultimainspecao.getCategoriaRisco().getCodigo().equals(3L)) {
			comunicacaoDTO.setGrauRisco(GrauRiscoEnum.ALTO.getDescricao());
		}
		
		EvacuacaoClient cliente =  new EvacuacaoClient(gateway, user, password);
		cliente.evacuarBarragem(comunicacaoDTO);

		request.setAttribute("mensagem","Processo de evacuação iniciado com sucesso");
		return "evacuacao/msgEvacuacao";
    }

}
