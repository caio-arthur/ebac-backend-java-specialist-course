package br.com.streetcoders.vendas.ConfigServidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServidorApplication.class, args);
	}

}
