package br.com.sga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
@EnableCaching
public class MonitoramentoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoApplication.class, args);
	}

	/**
	 * @return
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}
