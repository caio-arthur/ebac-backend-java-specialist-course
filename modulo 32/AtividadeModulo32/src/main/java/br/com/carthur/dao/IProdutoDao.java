package br.com.carthur.dao;

import br.com.carthur.domain.Produto;

public interface IProdutoDao {
	public Produto cadastrar(Produto produto);
	public Produto buscarPorId(Long id);
	void excluir(Produto produto);
}
