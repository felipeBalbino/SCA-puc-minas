package br.com.sga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SegurancaComunicacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurancaComunicacaoServiceApplication.class, args);
	}

}