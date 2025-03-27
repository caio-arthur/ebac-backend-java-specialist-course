package br.com.carthur.dao;

import java.util.List;

import br.com.carthur.domain.Produto;

public interface IProdutoDAO {
	public List<Produto> listar() throws Exception;
	public Produto consultar(String codigo) throws Exception;
	public Integer cadastrar(Produto produto) throws Exception;
	public Integer atualizar(Produto produto) throws Exception;
	public Integer deletar(Produto produto) throws Exception;
	
}
