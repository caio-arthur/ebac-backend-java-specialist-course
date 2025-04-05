package br.com.carthur.dao;

import br.com.carthur.domain.Acessorio;

public interface IAcessorioDAO {
	public Acessorio cadastrar(Acessorio acessorio);
	public void excluir(Acessorio acessorio);
	public Acessorio buscarPorId(Long id);
}
