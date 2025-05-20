package br.com.streetcoders.vendas.ProdutoService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.streetcoders.vendas.ProdutoService.repository")
public class MongoConfig {

}