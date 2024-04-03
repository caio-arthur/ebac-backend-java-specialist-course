package br.com.carthur.services;

import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.dao.generic.IGenericDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;
import br.com.carthur.services.generic.GenericService;

/**
 * @author caio.arthur
 */
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {
	
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
		//this.clienteDAO = clienteDAO;
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) {
		return this.dao.consultar(cpf);
	}


}
