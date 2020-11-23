package br.com.sga.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.binder.LenientDateParser;
import br.com.sga.client.EvacuacaoClient;
import br.com.sga.client.InspecaoClient;
import br.com.sga.dto.GrauRiscoEnum;
import br.com.sga.dto.Inspecao;
import br.com.sga.dto.PlanoAcao;
import br.com.sga.repository.filter.InspecaoFilter;


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
		
		PlanoAcao planoAcao = new PlanoAcao(codigo);
		planoAcao.setCodigoBarragem(codigo);
		if(ultimainspecao.getCategoriaRisco().getCodigo().equals(1L)) {
			planoAcao.setGrauRisco(GrauRiscoEnum.BAIXO);
		}else if(ultimainspecao.getCategoriaRisco().getCodigo().equals(2L)) {
			planoAcao.setGrauRisco(GrauRiscoEnum.MEDIO);
		}else if(ultimainspecao.getCategoriaRisco().getCodigo().equals(3L)) {
			planoAcao.setGrauRisco(GrauRiscoEnum.ALTO);
		}
		
		EvacuacaoClient cliente =  new EvacuacaoClient(gateway, user, password);
		cliente.evacuarBarragem(planoAcao);
		
		attr.addFlashAttribute("mensagem","Processo de evacuação iniciado com sucesso");
		return "/evacuacao/emsgEvacuacao";
    }

}
