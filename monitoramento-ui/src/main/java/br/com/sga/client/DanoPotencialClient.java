package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.DanoPotencial;

/**
 * @author sga
 *
 */
public class DanoPotencialClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/monitoramento/danopotencial";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public DanoPotencialClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @return
	 */
	public List<DanoPotencial> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<DanoPotencial[]> response = restTemplate.exchange(request, DanoPotencial[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param DanoPotencial
	 * @return
	 */
	public String save(DanoPotencial danoPotencial) {
		RequestEntity<DanoPotencial> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(danoPotencial);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
	
	/**
	 * @param id
	 * @return
	 */
	public DanoPotencial findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<DanoPotencial> response = restTemplate.exchange(request, DanoPotencial.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<DanoPotencial> response = restTemplate.exchange(request, DanoPotencial.class);

		return response.getStatusCode();
	}
	
	
	/**
	 * @param Ativo
	 * @return
	 */
	public String update(DanoPotencial tipoAtivo, Long id) {
		RequestEntity<DanoPotencial> request = RequestEntity.put(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial)
				.body(tipoAtivo);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}