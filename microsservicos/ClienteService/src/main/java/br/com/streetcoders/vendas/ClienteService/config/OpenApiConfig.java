package br.com.streetcoders.vendas.ClienteService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("1.0") String appVersion) {
		return new OpenAPI()
				          .info(new Info()
				          .title("Serviço de clientes")
				          .version(appVersion)
				          .description("Serviço para gerenciamento de clientes")
				          .termsOfService("http://swagger.io/terms/")
				          .license(new License().name("Apache 2.0").url("http://springdoc.org"))
				          .contact(new Contact().name("Caio Arthur").email("carthur_dev@hotmail.com")));
	}
}

