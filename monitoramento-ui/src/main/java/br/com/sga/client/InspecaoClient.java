package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.model.Inspecao;
import br.com.sga.model.LeituraSensor;

/**
 * @author SGA
 *
 */
public class InspecaoClient {

	private RestTemplate restTemplate;

	private String URI_BASE;	

	private String URL;

	private String URN_BASE = "/monitoramento/inspecao";

	private String URN_BASE_DAM = "/monitoramento/barragem/";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public InspecaoClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URL = url;

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**

	 * @return
	 */
	public List<Inspecao> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Inspecao[]> response = restTemplate.exchange(request, Inspecao[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param inspecao
	 * @return
	 */
	public String save(Inspecao Inspecao) {
		RequestEntity<Inspecao> request = RequestEntity
				.post(URI.create(URL + URN_BASE_DAM + Inspecao.getBarragem().getCodigo() + "/inspecao"))
				.header("Authorization", credencial).body(Inspecao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Inspecao findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Inspecao> response = restTemplate.exchange(request, Inspecao.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {	
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Inspecao> response = restTemplate.exchange(request, Inspecao.class);

		return response.getStatusCode();
	}
	
	/**
	 * @param Ativo
	 * @return
	 */
	public String update(Inspecao inspecao, Long id) {
		RequestEntity<Inspecao> request = RequestEntity.put(URI.create(URI_BASE + "/" + id)).header("Authorization", credencial)
				.body(inspecao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}