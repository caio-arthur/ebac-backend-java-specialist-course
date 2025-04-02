package br.com.carthur;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.carthur.dao.ClienteDaoMock;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;
import br.com.carthur.services.ClienteService;
import br.com.carthur.services.IClienteService;

/**
 * @author caio.arthur
 */
public class ClienteServiceTeste {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTeste() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		
	}
	
	@Test
	public void pesquisarCliente() throws DAOException, SQLException {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() throws DAOException, SQLException {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, SQLException {
		cliente.setNome("Rodrigo Pires");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Rodrigo Pires", cliente.getNome());
	}
}