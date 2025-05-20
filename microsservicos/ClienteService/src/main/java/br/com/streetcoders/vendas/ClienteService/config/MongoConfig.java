package br.com.streetcoders.vendas.ClienteService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.streetcoders.vendas.ClienteService.repository")
public class MongoConfig {

}
