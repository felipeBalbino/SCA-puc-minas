package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.Barragem;
import br.com.sga.dto.LeituraSensor;

/**
 * @author sga
 *
 */
public class LeituraSensorClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/monitoramento/sensor/leitura";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public LeituraSensorClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**

	 * @return
	 */
	public List<LeituraSensor> list() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE)).header("Authorization", credencial).build();

		ResponseEntity<LeituraSensor[]> response = restTemplate.exchange(request, LeituraSensor[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param leituraSensor
	 * @return
	 */
	public String save(LeituraSensor leituraSensor) {
		RequestEntity<LeituraSensor> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(leituraSensor);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public LeituraSensor findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial).build();

		ResponseEntity<LeituraSensor> response = restTemplate.exchange(request, LeituraSensor.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<LeituraSensor> response = restTemplate.exchange(request, LeituraSensor.class);

		return response.getStatusCode();
	}
	
	/**

	 * @return
	 */
	public List<LeituraSensor> listBySensor(Long codigoSensor) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + codigoSensor + "/leiturasensor")).header("Authorization", credencial).build();

		ResponseEntity<LeituraSensor[]> response = restTemplate.exchange(request, LeituraSensor[].class);

		return Arrays.asList(response.getBody());
	}
	
	/**
	 * @param Ativo
	 * @return
	 */
	public String update(LeituraSensor leituraSensor, Long id) {
		RequestEntity<LeituraSensor> request = RequestEntity.put(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial)
				.body(leituraSensor);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}