package br.com.carthur.dao;

import java.util.List;

import br.com.carthur.dao.generic.IGenericDAO;
import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;

public interface ICarroDAO extends IGenericDAO<Carro, Long> {

	void addAcessorio(Long idCarro, Long idAcessorio);

	List<Acessorio> getAcessoriosByCarroId(Long idCarro);

	void removeAcessorio(Long idCarro, Long idAcessorio);

}
