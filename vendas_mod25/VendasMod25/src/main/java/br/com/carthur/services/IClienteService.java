package br.com.carthur.services;

import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

/**
 * @author caio.arthur
 */
public interface IClienteService  {

	Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;

	Cliente buscarPorCPF(Long cpf);

	void excluir(Long cpf);

	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;

}
