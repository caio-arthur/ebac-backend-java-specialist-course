package br.com.carthur.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.carthur.domain.Cliente;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long>{

}