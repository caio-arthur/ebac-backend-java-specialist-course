package br.com.carthur.dao;

import java.util.Collection;
import br.com.carthur.domain.Cliente;
import java.util.List;

/**
*
* @author caio.arthur
*/

public interface IClienteDAO {
	
	public Boolean cadastrar(Cliente cliente);
    
    public void excluir(String cpf);
    
    public void alterar(Cliente cliente);
    
    public Cliente consultar(String cpf);

    public Collection<Cliente> buscarTodos();
}
