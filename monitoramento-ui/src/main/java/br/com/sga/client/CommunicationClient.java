package br.com.sga.client;

import java.net.URI;
import java.util.Base64;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.client.filter.CommunicationFilter;

/**
 * @author sga
 *
 */
public class CommunicationClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/security/communication";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public CommunicationClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param communication
	 * @return
	 */
	public String save(CommunicationFilter communicationFilter) {
		RequestEntity<CommunicationFilter> request = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(communicationFilter);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

}