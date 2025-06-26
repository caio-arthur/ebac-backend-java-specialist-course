package br.com.streetcoders.vendas.VendaService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.streetcoders.vendas.VendaService.domain.Produto;

@FeignClient(name = "produto", url = "http://localhost:8082/")
public interface IProdutoService {

    @RequestMapping(method = RequestMethod.GET, value = "/produto/{codigo}", produces = "application/json")
    Produto buscarProduto(@PathVariable("codigo") String codigoProduto);

}

