package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.model.Dam;

/**
 * @author sga
 *
 */
public class DamClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/monitoramento/dam";

	private String credencial;

	/**
	 * @param url
	 * @param usuario
	 * @param senha
	 */
	public DamClient(String url, String usuario, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = usuario + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param search
	 * @return
	 */
	public List<Dam> list(String search) {

		String path = URI_BASE;
		if (search != null) {
			path = URI_BASE + "?search=" + search;
		}
		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Dam[]> response = restTemplate.exchange(request, Dam[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param dam
	 * @return
	 */
	public String save(Dam dam) {
		RequestEntity<Dam> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(dam);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Dam findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Dam> response = restTemplate.exchange(request, Dam.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Dam> response = restTemplate.exchange(request, Dam.class);

		return response.getStatusCode();
	}
}