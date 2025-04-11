package br.com.carthur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.dao.ProdutoDAO;
import br.com.carthur.domain.Produto;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class ProdutoDAOTeste {
	
	private IProdutoDAO produtoDao;
	
	public ProdutoDAOTeste() {
		produtoDao = new ProdutoDAO();
	}
	
	@After
	public void end() throws DAOException, TableException, SQLException {
	    Collection<Produto> list = produtoDao.buscarTodos();
	    list.forEach(prod -> {
	        try {
	            produtoDao.excluir(prod);
	        } catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	@Test
	public void pesquisarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto();
		produtoDao.cadastrar(produto);
		
		Produto produtoConsultado = produtoDao.consultar(produto.getId());
		assertNotNull(produtoConsultado);
		
	}
	
	@Test
	public void salvarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto();
		produtoDao.cadastrar(produto);
		assertNotNull(produto);
		
		Produto produtoConsultado = produtoDao.consultar(produto.getId());
		assertNotNull(produtoConsultado);
		
		produtoDao.excluir(produto);
		
		Produto produtoConsultado1 = produtoDao.consultar(produto.getId());
		assertNull(produtoConsultado1);
	}
	
	@Test
	public void excluirProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto();
		produtoDao.cadastrar(produto);
		assertNotNull(produto);
		
		Produto produtoConsultado = produtoDao.consultar(produto.getId());
		assertNotNull(produtoConsultado);
		
		produtoDao.excluir(produto);
		Produto produtoConsultado1 = produtoDao.consultar(produto.getId());
		assertNull(produtoConsultado1);
	}

	@Test
	public void alterarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto();
		produtoDao.cadastrar(produto);
		assertNotNull(produto);
		
		produto.setNome("Produto Teste Alterado");
		produto.setDescricao("Descricao do Produto Teste Alterado");
		produto.setCategoria("Categoria Teste Alterado");
		produto.setValor(new BigDecimal(20.0));
		produtoDao.alterar(produto);
		assertEquals("Produto Teste Alterado", produto.getNome());
		
		produtoDao.excluir(produto);
		Produto produtoConsultado1 = produtoDao.consultar(produto.getId());
		assertNull(produtoConsultado1);
	}
	
	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto();
		produtoDao.cadastrar(produto);
		
		Produto produto1 = criarProduto();
		produtoDao.cadastrar(produto1);
		
		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		list.forEach(prod -> {
			try {
				produtoDao.excluir(prod);
			} catch (SQLException | DAOException | TipoChaveNaoEncontradaException e) {
				e.printStackTrace();
			}
		});	
		
		Collection<Produto> list1 = produtoDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}
	
	private Produto criarProduto() {
		Produto produto = new Produto();
		produto.setNome("Produto Teste");
		produto.setDescricao("Descricao do Produto Teste");
		produto.setCategoria("Categoria Teste");
		produto.setCodigo(UUID.randomUUID().toString());
		produto.setValor(new BigDecimal(10.0));
		
		return produto;
	}
	
}