package br.com.carthur;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.dao.ProdutoDAO;
import br.com.carthur.domain.Produto;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;
import junit.framework.Assert;

public class ProdutoDAOTeste {
	
	private IProdutoDAO produtoDao;

	private Produto produto;
	
	public ProdutoDAOTeste() {
		produtoDao = new ProdutoDAO();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
	}
	
	@Test
	public void pesquisar() {
		Produto produto = this.produtoDao.consultar(this.produto.getCodigo());
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		produto.setCodigo("C3");
		Boolean retorno = produtoDao.cadastrar(produto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		produtoDao.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarProduto() throws TipoChaveNaoEncontradaException {
		produto.setNome("Computador");
		produtoDao.alterar(produto);
		
		Assert.assertEquals("Computador", produto.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
