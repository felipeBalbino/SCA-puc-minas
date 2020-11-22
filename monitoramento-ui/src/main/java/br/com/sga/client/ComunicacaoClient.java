package br.com.sga.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sga.dto.Comunicacao;
import br.com.sga.dto.Inspecao;

/**
 * @author sga
 *
 */
public class ComunicacaoClient {

	private RestTemplate restTemplate;

	private String URI_BASE;

	private String URN_BASE = "/seguranca/comunicacao";

	private String credencial;

	/**
	 * @param url
	 * @param user
	 * @param senha
	 */
	public ComunicacaoClient(String url, String user, String senha) {
		restTemplate = new RestTemplate();

		URI_BASE = url.concat(URN_BASE);

		String credencialAux = user + ":" + senha;

		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	/**
	 * @param search
	 * @return
	 */
	public List<Comunicacao> list() {

		String path = URI_BASE;

		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).header("Authorization", credencial).build();

		ResponseEntity<Comunicacao[]> response = restTemplate.exchange(request, Comunicacao[].class);

		return Arrays.asList(response.getBody());
	}

	/**
	 * @param Comunicacao
	 * @return
	 */
	public String save(Comunicacao comunicacao) {
		RequestEntity<Comunicacao> request = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(comunicacao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * @param id
	 * @return
	 */
	public Comunicacao findById(Long id) {
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Comunicacao> response = restTemplate.exchange(request, Comunicacao.class);

		return response.getBody();
	}

	/**
	 * @param id
	 * @return
	 */
	public HttpStatus delete(Long id) {
		RequestEntity<Void> request = RequestEntity.delete(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).build();

		ResponseEntity<Comunicacao> response = restTemplate.exchange(request, Comunicacao.class);

		return response.getStatusCode();
	}

	/**
	 * @param Comunicacao
	 * @return
	 */
	public String update(Comunicacao comunicacao, Long id) {
		RequestEntity<Comunicacao> request = RequestEntity.put(URI.create(URI_BASE + "/" + id))
				.header("Authorization", credencial).body(comunicacao);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	/**
	 * 
	 * @return
	 */
	public Comunicacao ultimaComunicacaoByIdBarragem(Long idBarragem) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/" + idBarragem + "/ultima"))
				.header("Authorization", credencial).build();

		ResponseEntity<Comunicacao> response = restTemplate.exchange(request, Comunicacao.class);

		return response.getBody();
	}
}