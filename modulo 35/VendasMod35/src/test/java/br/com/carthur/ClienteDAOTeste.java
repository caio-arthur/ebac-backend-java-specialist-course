package br.com.carthur;

import static org.junit.Assert.assertEquals;

/**
 * @author caio.arthur
 */


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.carthur.dao.ClienteDAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class ClienteDAOTeste {
	
	private IClienteDAO clienteDao;
	private Random rd;
	
	public ClienteDAOTeste() {
		clienteDao = new ClienteDAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException, TableException, SQLException {
	    Collection<Cliente> list = clienteDao.buscarTodos();
	    list.forEach(cli -> {
	        try {
	            clienteDao.excluir(cli);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		
		Cliente clienteConsultado1 = clienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
	}
	
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteDao.excluir(cliente);
		Cliente clienteConsultado1 = clienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clienteDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		cliente.setNome("Arthur");
		Cliente clienteAlterado = clienteDao.alterar(cliente);
		assertEquals("Arthur", clienteAlterado.getNome());
		
		clienteDao.excluir(cliente);
		Cliente clienteConsultado1 = clienteDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException, TableException, SQLException, MaisDeUmRegistroException {
		Cliente cliente = criarCliente();
		clienteDao.cadastrar(cliente);
		
		Cliente cliente1 = criarCliente();
		clienteDao.cadastrar(cliente1);
		
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
				e.printStackTrace();
			}
		});
		
		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	private Cliente criarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = new Cliente();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Caio Arthur");
		cliente.setIdade(22);
		cliente.setCidade("Sete Lagoas");
		cliente.setEstado("MG");
		cliente.setTelefone(31995326439L);		
		
		return cliente;
	}
}
