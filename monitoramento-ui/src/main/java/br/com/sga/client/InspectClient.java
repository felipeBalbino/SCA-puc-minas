package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.model.Inspect;

/**
 * @author SGA
 *
 */
public class InspectClient {

	private RestTemplate restTemplate;

	private String URI_BASE;	

	private String URL;

	private String URN_BASE = "/monitoramento/inspect";

	private String URN_BASE_DAM = "/monitoramento/dam/";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public InspectClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URL = url;

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**

	 * @return
	 */
	public List<Inspect> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Inspect[]> response = restTemplate.exchange(request, Inspect[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param inspect
	 * @return
	 */
	public String save(Inspect Inspect) {
		RequestEntity<Inspect> request = RequestEntity
				.post(URI.create(URL + URN_BASE_DAM + Inspect.getDam().getId() + "/inspect"))
				.header("Authorization", credencial).body(Inspect);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Inspect findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Inspect> response = restTemplate.exchange(request, Inspect.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {	
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Inspect> response = restTemplate.exchange(request, Inspect.class);

		return response.getStatusCode();
	}
}