package br.com.sga.client;

import java.net.URI;
import java.util.Base64;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
	 * @param usuario
	 * @param senha
	 */
	public CommunicationClient(String url, String usuario, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = usuario + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param communication
	 * @return
	 */
	public String save(Communication communication) {
		RequestEntity<Communication> request = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(communication);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

}