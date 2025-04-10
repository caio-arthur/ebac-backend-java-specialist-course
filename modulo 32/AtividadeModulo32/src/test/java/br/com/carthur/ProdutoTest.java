package br.com.carthur;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import br.com.carthur.dao.IProdutoDao;
import br.com.carthur.dao.ProdutoDao;
import br.com.carthur.domain.Produto;

public class ProdutoTest {
	
	private IProdutoDao produtoDao;
	
	public ProdutoTest() {
		produtoDao = new ProdutoDao();
	}
	
	@Test
	public void cadastrarProduto() {
		//Cadastrar um produto
		Produto produto = new Produto();
		produto.setNome("Produto Teste");
		produto.setDescricao("Descricao do Produto Teste");
		produto.setPreco(100.0);
		produtoDao.cadastrar(produto);
		
		//Consultar o produto cadastrado
		
		Produto produtoConsultado = produtoDao.buscarPorId(produto.getId());
		assertTrue(produtoConsultado != null);
		
		//Excluir o produto cadastrado
		produtoDao.excluir(produtoConsultado);
		
		//Verificar se o produto foi excluído
		Produto produtoExcluido = produtoDao.buscarPorId(produtoConsultado.getId());
		assertTrue(produtoExcluido == null);
	}
	
	
	
	
	
}
