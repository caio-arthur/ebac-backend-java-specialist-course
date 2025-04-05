package br.com.carthur.dao;

import java.util.List;

import br.com.carthur.domain.Carro;

public interface ICarroDAO {
	public Carro buscarPorId(Long id);
	public List<Carro> buscarTodos();
	public Carro cadastrar(Carro entity);
	public Carro alterar(Carro entity);
	public void excluir(Carro entity);
}
