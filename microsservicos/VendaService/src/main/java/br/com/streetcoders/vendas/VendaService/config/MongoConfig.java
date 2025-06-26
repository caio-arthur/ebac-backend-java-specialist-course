package br.com.streetcoders.vendas.VendaService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.streetcoders.vendas.VendaService.repository")
public class MongoConfig {

}