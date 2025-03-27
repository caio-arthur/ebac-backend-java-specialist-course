package br.com.carthur;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import br.com.carthur.dao.ProdutoDAO;
import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.domain.Produto;


public class ProdutoTestes {
	
	@Test
	public void cadastrarTeste() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();
		
		produto.setCodigo("7");
		produto.setNome("Geleia");
		produto.setDescricao("Geleia de morango");
		produto.setValor(BigDecimal.valueOf(5.0));
		
		Integer quantidade = dao.cadastrar(produto);
		assertTrue(quantidade == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer countDel = dao.deletar(produto);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTeste() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("7");
		produto.setNome("Geleia");
		produto.setDescricao("Geleia de morango");
		produto.setValor(BigDecimal.valueOf(5.0));
		
		Integer countCad = dao.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = dao.consultar("7");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer countDel = dao.deletar(produtoBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void excluirTeste() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("7");
		produto.setNome("Geleia");
		produto.setDescricao("Geleia de morango");
		produto.setValor(BigDecimal.valueOf(5.0));
		Integer countCad = dao.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = dao.consultar("7");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer countDel = dao.deletar(produtoBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTeste() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("7");
		produto.setNome("Geleia");
		produto.setDescricao("Geleia de morango");
		produto.setValor(BigDecimal.valueOf(5.0));
		Integer countCad = dao.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produto2 = new Produto();
		produto2.setCodigo("14");
		produto2.setNome("Pão");
		produto2.setDescricao("Pão francês");
		produto2.setValor(BigDecimal.valueOf(0.5));
		Integer countCad2 = dao.cadastrar(produto2);
		assertTrue(countCad2 == 1);
		
		List<Produto> produtos = dao.listar();
		assertNotNull(produtos);
		assertEquals(2, produtos.size());
		
		int countDel = 0;
		for (Produto p : produtos) {
			dao.deletar(p);
			countDel++;
		}
		assertEquals(2, countDel);
		
		produtos = dao.listar();
		assertEquals(produtos.size(), 0);
	}
	
	@Test
	public void atualizarTeste() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("7");
		produto.setNome("Geleia");
		produto.setDescricao("Geleia de morango");
		produto.setValor(BigDecimal.valueOf(5.0));
		Integer countCad = dao.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = dao.consultar("7");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getDescricao(), produtoBD.getDescricao());
		assertTrue(produto.getValor().compareTo(produtoBD.getValor()) == 0);
		
		produtoBD.setCodigo("14");
		produtoBD.setNome("Geléia de morango");
		produtoBD.setDescricao("Geléia de morango saborosa");
		produtoBD.setValor(BigDecimal.valueOf(6.0));
		Integer countUpdate = dao.atualizar(produtoBD);
		assertTrue(countUpdate == 1);
		
		Produto produtoBD1 = dao.consultar("7");
		assertNull(produtoBD1);
		
		Produto produtoBD2 = dao.consultar("14");
		assertNotNull(produtoBD2);
		assertEquals(produtoBD.getId(), produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		assertEquals(produtoBD.getDescricao(), produtoBD2.getDescricao());
		assertTrue(produtoBD.getValor().compareTo(produtoBD2.getValor()) == 0);
		
		List<Produto> produtos = dao.listar();
		for (Produto p : produtos) {
			dao.deletar(p);
		}
	}
	
}
