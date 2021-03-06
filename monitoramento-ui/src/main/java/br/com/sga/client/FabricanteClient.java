package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.Fabricante;

/**
 * @author sga
 *
 */
public class FabricanteClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/ativos/fabricante";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public FabricanteClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**

	 * @return
	 */
	public List<Fabricante> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Fabricante[]> response = restTemplate.exchange(request, Fabricante[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param Fabricante
	 * @return
	 */
	public String save(Fabricante Fabricante) {
		RequestEntity<Fabricante> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(Fabricante);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Fabricante findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Fabricante> response = restTemplate.exchange(request, Fabricante.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Fabricante> response = restTemplate.exchange(request, Fabricante.class);

		return response.getStatusCode();
	}
	
	/**
	 * @param Ativo
	 * @return
	 */
	public String update(Fabricante fabricante, Long id) {
		RequestEntity<Fabricante> request = RequestEntity.put(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial)
				.body(fabricante);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}