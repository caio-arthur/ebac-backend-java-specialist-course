package br.com.carthur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import br.com.carthur.dao.ClienteDAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.domain.Cliente;


public class ClienteTestes {
	
	@Test
	public void cadastrarTeste() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente();
		
		cliente.setCodigo("7");
		cliente.setNome("Caio Arthur");
		Integer quantidade = dao.cadastrar(cliente);
		assertTrue(quantidade == 1);
		
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = dao.deletar(cliente);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTeste() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("7");
		cliente.setNome("Caio Arthur");
		Integer countCad = dao.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = dao.consultar("7");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = dao.deletar(clienteBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void excluirTeste() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("7");
		cliente.setNome("Caio Arthur");
		Integer countCad = dao.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = dao.consultar("7");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = dao.deletar(clienteBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTeste() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("7");
		cliente.setNome("Caio");
		Integer countCad = dao.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clientes = new Cliente();
		clientes.setCodigo("14");
		clientes.setNome("Arthur");
		Integer countCad2 = dao.cadastrar(clientes);
		assertTrue(countCad2 == 1);
		
		List<Cliente> list = dao.listar();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Cliente cli : list) {
			dao.deletar(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = dao.listar();
		assertEquals(list.size(), 0);
		
	}
	
	@Test
	public void atualizarTeste() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("7");
		cliente.setNome("Caio");
		Integer countCad = dao.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = dao.consultar("7");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		clienteBD.setCodigo("14");
		clienteBD.setNome("Arthur");
		Integer countUpdate = dao.atualizar(clienteBD);
		assertTrue(countUpdate == 1);
		
		Cliente clienteBD1 = dao.consultar("7");
		assertNull(clienteBD1);
		
		Cliente clienteBD2 = dao.consultar("14");
		assertNotNull(clienteBD2);
		assertEquals(clienteBD.getId(), clienteBD2.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		List<Cliente> list = dao.listar();
		for (Cliente cli : list) {
			dao.deletar(cli);
		}
	}
}
