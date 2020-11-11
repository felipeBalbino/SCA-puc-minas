package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.PlanoAcao;

/**
 * @author sga
 *
 */
public class PlanoAcaoClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/seguranca/planoacao";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public PlanoAcaoClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param search
	 * @return
	 */
	public List<PlanoAcao> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<PlanoAcao[]> response = restTemplate.exchange(request, PlanoAcao[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param PlanoAcao
	 * @return
	 */
	public String save(PlanoAcao planoAcao) {
		RequestEntity<PlanoAcao> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(planoAcao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public PlanoAcao findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<PlanoAcao> response = restTemplate.exchange(request, PlanoAcao.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<PlanoAcao> response = restTemplate.exchange(request, PlanoAcao.class);

		return response.getStatusCode();
	}

	/**
	 * @param PlanoAcao
	 * @return
	 */
	public String update(PlanoAcao planoAcao, Long id) {
		RequestEntity<PlanoAcao> request = RequestEntity.put(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).body(planoAcao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}