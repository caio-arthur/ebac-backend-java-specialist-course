package br.com.carthur;

import br.com.carthur.domain.Produto;
import br.com.carthur.exception.*;
import br.com.carthur.services.IProdutoService;
import br.com.carthur.services.ProdutoService;
import junit.framework.Assert;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.dao.ProdutoDaoMock;

public class ProdutoServiceTeste {

	private IProdutoService produtoService;
	
	private Produto produto;
	
	public ProdutoServiceTeste() {
		IProdutoDAO dao = new ProdutoDaoMock();
		produtoService = new ProdutoService(dao);
	}
	
	@Before
	public void init() {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
	}
	
	@Test
	public void pesquisar() throws DAOException, SQLException {
		Produto produtor = this.produtoService.consultar(produto.getCodigo());
		assertNotNull(produtor);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = produtoService.cadastrar(produto);
		assertTrue(retorno);
	}
	
	@Test
	public void excluir() throws DAOException, SQLException {
		produtoService.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, SQLException {
		produto.setNome("Rodrigo Pires");
		produtoService.alterar(produto);
		
		Assert.assertEquals("Rodrigo Pires", produto.getNome());
	}
}