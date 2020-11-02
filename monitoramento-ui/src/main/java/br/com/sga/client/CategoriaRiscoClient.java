package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.model.CategoriaRisco;

/**
 * @author sga
 *
 */
public class CategoriaRiscoClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/monitoramento/categoriarisco";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public CategoriaRiscoClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @return
	 */
	public List<CategoriaRisco> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<CategoriaRisco[]> response = restTemplate.exchange(request, CategoriaRisco[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param CategoriaRisco
	 * @return
	 */
	public String save(CategoriaRisco categoriaRisco) {
		RequestEntity<CategoriaRisco> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(categoriaRisco);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
	
	/**
	 * @param id
	 * @return
	 */
	public CategoriaRisco findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<CategoriaRisco> response = restTemplate.exchange(request, CategoriaRisco.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<CategoriaRisco> response = restTemplate.exchange(request, CategoriaRisco.class);

		return response.getStatusCode();
	}
	
	
	/**
	 * @param Ativo
	 * @return
	 */
	public String update(CategoriaRisco categoriaRisco, Long id) {
		RequestEntity<CategoriaRisco> request = RequestEntity.put(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial)
				.body(categoriaRisco);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}