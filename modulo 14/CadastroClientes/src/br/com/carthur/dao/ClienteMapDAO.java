package br.com.carthur.dao;

import br.com.carthur.domain.Cliente;
import java.util.*;


/**
*
* @author caio.arthur
*/
public class ClienteMapDAO implements IClienteDAO {

	private Map<String, Cliente> map;
	
	public ClienteMapDAO() {
		map = new TreeMap<>();
	}
	
	@Override
	public Boolean cadastrar(Cliente cliente) {
	    // Remover espaços em branco do CPF
	    String cpfLimpo = cliente.getCpf().replaceAll("\\s+", "");
	    
	    if (map.containsKey(cpfLimpo)) {
	        return false;
	    }
	    
	    cliente.setCpf(cpfLimpo);
	    
	    map.put(cliente.getCpf(), cliente);
	    
	    return true;
	}


	@Override
	public void excluir(String cpf) {
		Cliente clienteCadastrado = map.get(cpf);
		
		if (clienteCadastrado != null) {
			map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
		}
		
	}

	@Override
	public void alterar(Cliente cliente) {
		Cliente clienteCadastrado = map.get(cliente.getCpf());

		if (clienteCadastrado != null) {
			clienteCadastrado.setNome(cliente.getNome());
	        clienteCadastrado.setTel(cliente.getTel());
	        clienteCadastrado.setNumero(cliente.getNumero());
	        clienteCadastrado.setEnd(cliente.getEnd());
	        clienteCadastrado.setCidade(cliente.getCidade());
	        clienteCadastrado.setEstado(cliente.getEstado());
		}
	}

	@Override
	public Cliente consultar(String cpf) {
	    return this.map.get(cpf);
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		return this.map.values();
	}

}
