package br.com.carthur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.carthur.dao.EstoqueDAO;
import br.com.carthur.dao.IEstoqueDAO;
import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.dao.ProdutoDAO;
import br.com.carthur.domain.Estoque;
import br.com.carthur.domain.Produto;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;
import junit.framework.Assert;

public class ProdutoDAOTeste {
	
	private IProdutoDAO produtoDao;
	private IEstoqueDAO estoqueDao;

	public ProdutoDAOTeste() {
		produtoDao = new ProdutoDAO();
		estoqueDao = new EstoqueDAO();
	}
	
	@After
	public void end() throws DAOException, TableException, SQLException {
		Collection<Produto> list = produtoDao.buscarTodos();
		list.forEach(prod -> {
			try {
				produtoDao.excluir(prod.getCodigo());
			} catch (DAOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private Produto criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setCategoria("Categoria 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
		
		// Cria o estoque deste produto
		Estoque estoque = new Estoque();
		estoque.setIdProduto(produto.getId());
		estoque.setQuantidade(100);
		estoque.setCodigo(produto.getCodigo());
		estoqueDao.cadastrar(estoque);
		
		return produto;
	}
	
	private void excluir(String valor) throws DAOException, SQLException {
		this.estoqueDao.excluir(valor);
		this.produtoDao.excluir(valor);
	}
	
	@Test
	public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException, SQLException {
		Produto produto = criarProduto("A1");
		assertNotNull(produto);
		Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
		assertNotNull(produtoDB);
		excluir(produtoDB.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException, SQLException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProduto("A2");
		assertNotNull(produto);
		excluir(produto.getCodigo());
	}
	
	@Test
	public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto("A3");
		assertNotNull(produto);
		excluir(produto.getCodigo());
		Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
		assertNull(produtoBD);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException, SQLException {
		Produto produto = criarProduto("A4");
		produto.setNome("Caio Arthur");
		produto.setCategoria("Categoria 2");
		produtoDao.alterar(produto);
		Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertEquals("Caio Arthur", produtoBD.getNome());
		
		excluir(produto.getCodigo());
		Produto produtoBD1 = this.produtoDao.consultar(produto.getCodigo());
		assertNull(produtoBD1);
	}
	
	@Test
	public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException, TableException, SQLException, MaisDeUmRegistroException {
		criarProduto("A5");
		criarProduto("A6");
		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		for (Produto prod : list) {
			excluir(prod.getCodigo());
		}
		
		list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 0);
		
	}
}