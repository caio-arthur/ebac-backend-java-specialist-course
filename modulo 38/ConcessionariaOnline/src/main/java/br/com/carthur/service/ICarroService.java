package br.com.carthur.service;

import java.util.List;

import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;
import br.com.carthur.services.generic.IGenericService;

public interface ICarroService extends IGenericService<Carro, Long>  {

	void addAcessorio(Long idCarro, Long idAcessorio);

	List<Acessorio> buscarTodosAcessorios(Long idCarro);

	void excluirAcessorio(Long idCarro, Long idAcessorio);

}
