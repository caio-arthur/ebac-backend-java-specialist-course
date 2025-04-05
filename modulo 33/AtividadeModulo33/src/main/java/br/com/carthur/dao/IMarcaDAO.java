package br.com.carthur.dao;

import br.com.carthur.domain.Marca;

public interface IMarcaDAO {
	public Marca cadastrar(Marca marca);
	public void excluir(Marca marca);
	public Marca buscarPorId(Long id);
}
