/**
 * 
 */
package br.com.carthur.dao;

import java.util.List;

import br.com.carthur.domain.Cliente;

/**
 * 
 */
public interface IClienteDAO {
	public List<Cliente> listar() throws Exception;
	public Cliente consultar(String codigo) throws Exception;
	public Integer cadastrar(Cliente cliente) throws Exception;
	public Integer atualizar(Cliente cliente) throws Exception;
	public Integer deletar(Cliente cliente) throws Exception;
	
}
