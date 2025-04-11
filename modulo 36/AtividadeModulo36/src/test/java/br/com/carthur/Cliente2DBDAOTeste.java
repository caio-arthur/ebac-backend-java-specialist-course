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

import br.com.carthur.dao.ClienteDB1DAO;
import br.com.carthur.dao.ClienteDB2DAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;


public class Cliente2DBDAOTeste {
	
	private IClienteDAO clienteDB1Dao;
	private IClienteDAO clienteDB2Dao;
	private Random rd;
	
	public Cliente2DBDAOTeste() {
		clienteDB1Dao = new ClienteDB1DAO();
		clienteDB2Dao = new ClienteDB2DAO();
		rd = new Random();
	}
	
	@After
	public void end() throws DAOException, TableException, SQLException {
	    Collection<Cliente> list = clienteDB1Dao.buscarTodos();
	    list.forEach(cli -> {
	        try {
	            clienteDB1Dao.excluir(cli);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	    
	    list = clienteDB2Dao.buscarTodos();
	    list.forEach(cli -> {
	        try {
	            clienteDB2Dao.excluir(cli);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	@Test
	public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDB1Dao.cadastrar(cliente);
		
		Cliente clienteConsultado = clienteDB1Dao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);
		
		Cliente clienteDB2 = criarCliente();
		clienteDB2Dao.cadastrar(clienteDB2);
		
		clienteConsultado = clienteDB2Dao.consultar(clienteDB2.getId());
		Assert.assertNotNull(clienteConsultado);
		
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		Cliente retorno = clienteDB1Dao.cadastrar(cliente);
		assertNotNull(retorno);
		
		Cliente clienteConsultado = clienteDB1Dao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB1Dao.excluir(cliente);
		
		Cliente clienteConsultado1 = clienteDB1Dao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		retorno = clienteDB2Dao.cadastrar(clienteDB2);
		assertNotNull(retorno);
		
		clienteConsultado = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB2Dao.excluir(clienteDB2);
		
		clienteConsultado1 = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void excluirCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDB1Dao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clienteDB1Dao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB1Dao.excluir(cliente);
		Cliente clienteConsultado1 = clienteDB1Dao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		clienteDB2Dao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		clienteConsultado = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB2Dao.excluir(clienteDB2);
		clienteConsultado1 = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDB1Dao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Cliente clienteConsultado = clienteDB1Dao.consultar(cliente.getId());
		assertNotNull(clienteConsultado);
		
		cliente.setNome("Arthur");
		Cliente clienteAlterado = clienteDB1Dao.alterar(cliente);
		assertEquals("Arthur", clienteAlterado.getNome());
		
		clienteDB1Dao.excluir(cliente);
		Cliente clienteConsultado1 = clienteDB1Dao.consultar(cliente.getId());
		assertNull(clienteConsultado1);
		
		Cliente clienteDB2 = criarCliente();
		clienteDB2Dao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		clienteConsultado = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNotNull(clienteConsultado);
		
		clienteDB2.setNome("Arthur");
		clienteAlterado = clienteDB2Dao.alterar(clienteDB2);
		assertEquals("Arthur", clienteAlterado.getNome());
		
		clienteDB2Dao.excluir(clienteDB2);
		clienteConsultado1 = clienteDB2Dao.consultar(clienteDB2.getId());
		assertNull(clienteConsultado1);
		
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Cliente cliente = criarCliente();
		clienteDB1Dao.cadastrar(cliente);
		assertNotNull(cliente);
		
		Collection<Cliente> list = clienteDB1Dao.buscarTodos();
		assertTrue(list.size() > 0);
		
		Cliente clienteDB2 = criarCliente();
		clienteDB2Dao.cadastrar(clienteDB2);
		assertNotNull(clienteDB2);
		
		list = clienteDB2Dao.buscarTodos();
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
