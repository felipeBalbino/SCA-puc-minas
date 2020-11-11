package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.Pessoa;

/**
 * @author sga
 *
 */
public class PessoaClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/seguranca/pessoa";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public PessoaClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param search
	 * @return
	 */
	public List<Pessoa> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Pessoa[]> response = restTemplate.exchange(request, Pessoa[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param Pessoa
	 * @return
	 */
	public String save(Pessoa pessoa) {
		RequestEntity<Pessoa> request = RequestEntity.post(URI.create(URI_BASE)).header("Authorization", credencial)
				.body(pessoa);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Pessoa findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Pessoa> response = restTemplate.exchange(request, Pessoa.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Pessoa> response = restTemplate.exchange(request, Pessoa.class);

		return response.getStatusCode();
	}

	/**
	 * @param Pessoa
	 * @return
	 */
	public String update(Pessoa pessoa, Long id) {
		RequestEntity<Pessoa> request = RequestEntity.put(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).body(pessoa);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}
}