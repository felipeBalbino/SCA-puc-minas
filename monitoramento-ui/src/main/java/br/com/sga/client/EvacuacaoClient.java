package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.Comunicacao;
import br.com.sga.dto.Fabricante;
import br.com.sga.dto.Inspecao;
import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
public class EvacuacaoClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/seguranca/evacuacao";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public EvacuacaoClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}



	/**
	 * @param Comunicacao
	 * @return
	 */
	public String evacuarBarragem(PlanoAcao planoAcao) {
		RequestEntity<PlanoAcao> request = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(planoAcao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}


}