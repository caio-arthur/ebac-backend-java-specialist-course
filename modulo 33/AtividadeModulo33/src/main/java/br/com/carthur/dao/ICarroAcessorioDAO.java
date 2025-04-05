package br.com.carthur.dao;

import java.util.List;

import br.com.carthur.domain.Acessorio;

public interface ICarroAcessorioDAO {
	public void cadastrar(Long idAcessorio, Long idCarro);
	public void excluir(Long idAcessorio, Long idCarro);
	public List<Acessorio> buscarAcessoriosPorCarroId(Long idCarro);
}
