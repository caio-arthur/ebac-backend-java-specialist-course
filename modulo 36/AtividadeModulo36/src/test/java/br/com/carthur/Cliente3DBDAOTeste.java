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

import br.com.carthur.dao.ClienteDB2DAO;
import br.com.carthur.dao.ClienteDB3DAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;



public class Cliente3DBDAOTeste {
	
	private IClienteDAO clientePostgresDao;
	private IClienteDAO clienteMySQLDao;
	private Random rd;
	
	public Cliente3DBDAOTeste() {
		clientePostgresDao = new ClienteDB2DAO();
		clienteMySQLDao = new ClienteDB3DAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException, TableException, SQLException {
	    Collection<Cliente> list = clientePostgresDao.buscarTodos();
	    list.forEach(cli -> {
	        try {
	        	clientePostgresDao.excluir(cli);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	    
	    list = clienteMySQLDao.buscarTodos();
	    list.forEach(cli -> {
	        try {
	        	clienteMySQLDao.excluir(cli);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clientePostgresDao.cadastrar(cliente);
		
		Cliente clienteConsultado = clientePostgresDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		Cliente clienteDB2 = criarCliente();
		clienteMySQLDao.cadastrar(clienteDB2);
		
		clienteConsultado = clienteMySQLDao.consultar(clienteDB2.getId());
		Assert.assertNotNull(clienteConsultado);
		
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		Cliente retorno = clientePostgresDao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clientePostgresDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clientePostgresDao.excluir(cliente);
		
		Cliente clienteConsultado1 = clientePostgresDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		retorno = clientePostgresDao.cadastrar(clienteDB2);
		assertNotNull(retorno);
		
		clienteConsultado = clientePostgresDao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clientePostgresDao.excluir(clienteDB2);
		
		clienteConsultado1 = clientePostgresDao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clientePostgresDao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clientePostgresDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clientePostgresDao.excluir(cliente);
		Cliente clienteConsultado1 = clientePostgresDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		clientePostgresDao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		clienteConsultado = clientePostgresDao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clientePostgresDao.excluir(clienteDB2);
		clienteConsultado1 = clientePostgresDao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clientePostgresDao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clientePostgresDao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		cliente.setNome("Arthur");
		Cliente clienteAlterado = clientePostgresDao.alterar(cliente);
		assertEquals("Arthur", clienteAlterado.getNome());
		
		clientePostgresDao.excluir(cliente);
		Cliente clienteConsultado1 = clientePostgresDao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		clientePostgresDao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		clienteConsultado = clientePostgresDao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB2.setNome("Arthur");
		clienteAlterado = clientePostgresDao.alterar(clienteDB2);
		assertEquals("Arthur", clienteAlterado.getNome());
		
		clientePostgresDao.excluir(clienteDB2);
		clienteConsultado1 = clientePostgresDao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clientePostgresDao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Collection<Cliente> list = clientePostgresDao.buscarTodos();
		assertTrue(list.size() > 0);
		
		Cliente clienteDB2 = criarCliente();
		clientePostgresDao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		list = clientePostgresDao.buscarTodos();
		assertTrue(list.size() > 0);
		
	}
	
	private Cliente criarCliente() {
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
