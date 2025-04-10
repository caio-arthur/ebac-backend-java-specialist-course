package br.com.carthur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.carthur.dao.ClienteDAO;
import br.com.carthur.dao.IClienteDAO;
import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.dao.IVendaDAO;
import br.com.carthur.dao.IVendaExclusaoDAO;
import br.com.carthur.dao.ProdutoDAO;
import br.com.carthur.dao.VendaDAO;
import br.com.carthur.dao.VendaExclusaoDAO;
import br.com.carthur.domain.Cliente;
import br.com.carthur.domain.Produto;
import br.com.carthur.domain.Venda;
import br.com.carthur.domain.Venda.Status;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class VendaDAOTeste {
	
	private IVendaDAO vendaDao;
	private IVendaExclusaoDAO vendaExclusaoDao;
	private IClienteDAO clienteDao;
	private IProdutoDAO produtoDao;
	
	private Random rd;
	private Cliente cliente;
	private Produto produto;
	
	public VendaDAOTeste() {
		vendaDao = new VendaDAO();
		vendaExclusaoDao = new VendaExclusaoDAO();
		clienteDao = new ClienteDAO();
		produtoDao = new ProdutoDAO();
		rd = new Random();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		this.cliente = cadastrarCliente();
		this.produto = cadastrarProduto("A1", BigDecimal.TEN);
	}
	
	@After
	public void end() throws DAOException, SQLException, TableException, TipoChaveNaoEncontradaException {
		excluirVendas();
		excluirProdutos();
		clienteDao.excluir(this.cliente);
	}
	

	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		Venda venda = criarVenda("A1");
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		Venda vendaConsultada = vendaDao.consultar(venda.getId());
		assertNotNull(vendaConsultada);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Venda venda = criarVenda("A2");
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
		
		Venda vendaConsultada = vendaDao.consultar(venda.getId());
		assertTrue(vendaConsultada.getId() != null);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	} 
	
	
	@Test
	public void cancelarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A3";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		retorno.setStatus(Status.CANCELADA);
		vendaDao.cancelarVenda(retorno);
		
		Venda vendaConsultada = vendaDao.consultar(venda.getId());
		assertEquals(codigoVenda, vendaConsultada.getCodigo());
		assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
	}
	
	@Test
	public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A4";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		vendaConsultada.adicionarProduto(produto, 1);
		
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A5";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		vendaConsultada.adicionarProduto(prod, 1);
		
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));	
	} 
	
	@Test(expected = RollbackException.class)
	public void salvarVendaMesmoCodigoExistente() throws TipoChaveNaoEncontradaException, DAOException {
		Venda venda = criarVenda("A6");
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		
		Venda venda1 = criarVenda("A6");
		Venda retorno1 = vendaDao.cadastrar(venda1);
		assertNull(retorno1);
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A7";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
		valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A8";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
		valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerTodosProdutos() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A9";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
		BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
		assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
		
		
		vendaConsultada.removerTodosProdutos();
		assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 0);
		assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(0)));
		assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void finalizarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A10";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		venda.setStatus(Status.CONCLUIDA);
		vendaDao.finalizarVenda(venda);
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException, SQLException {
		String codigoVenda = "A11";
		Venda venda = criarVenda(codigoVenda);
		Venda retorno = vendaDao.cadastrar(venda);
		assertNotNull(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		venda.setStatus(Status.CONCLUIDA);
		vendaDao.finalizarVenda(venda);
		
		Venda vendaConsultada = vendaDao.consultarComCollection(venda.getId());
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
		
		vendaConsultada.adicionarProduto(this.produto, 1);
	}

	private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Produto produto = new Produto();
		produto.setNome("Produto Teste");
		produto.setDescricao("Descricao do Produto Teste");
		produto.setCategoria("Categoria Teste");
		produto.setCodigo(codigo);
		produto.setValor(valor);
		produtoDao.cadastrar(produto);
		
		return produto;
	}

	private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(rd.nextLong());
		cliente.setNome("Caio Arthur");
		cliente.setIdade(22);
		cliente.setCidade("Sete Lagoas");
		cliente.setEstado("MG");
		cliente.setTelefone(31995326439L);	
		clienteDao.cadastrar(cliente);
		return cliente;
	}
	
	private Venda criarVenda(String codigo) {
		Venda venda = new Venda();
		venda.setCodigo(codigo);
		venda.setDataVenda(Instant.now());
		venda.setCliente(this.cliente);
		venda.setStatus(Status.INICIADA);
		venda.adicionarProduto(this.produto, 2);
		return venda;
	}
	
	private void excluirVendas() throws DAOException, TableException, SQLException {
		Collection<Venda> list = this.vendaExclusaoDao.buscarTodos();
		list.forEach(venda -> {
			try {
				this.vendaExclusaoDao.excluir(venda);
			} catch (DAOException | SQLException | TipoChaveNaoEncontradaException e) {
				e.printStackTrace();
			}
		});
	}

	private void excluirProdutos() throws DAOException, TableException, SQLException {
		Collection<Produto> list = this.produtoDao.buscarTodos();
		list.forEach(produto -> {
			try {
				this.produtoDao.excluir(produto);
			} catch (DAOException | SQLException | TipoChaveNaoEncontradaException e) {
				e.printStackTrace();
			}
		});
	}
	
}
