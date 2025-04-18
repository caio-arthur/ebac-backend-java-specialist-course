package br.com.carthur.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.carthur.dao.ICarroDAO;
import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;
import br.com.carthur.services.generic.GenericService;

@Stateless
public class CarroService extends GenericService<Carro, Long> implements ICarroService {
	
	private ICarroDAO carroDAO;
	
	@Inject
	public CarroService(ICarroDAO carroDAO) {
		super(carroDAO);
		this.carroDAO = carroDAO;
	}
	
	@Override
	public void addAcessorio(Long idCarro, Long idAcessorio) {
		carroDAO.addAcessorio(idCarro, idAcessorio);
	}
	
	@Override
	public List<Acessorio> buscarTodosAcessorios(Long idCarro) {
		return carroDAO.getAcessoriosByCarroId(idCarro);
	}
	
	@Override
	public void excluirAcessorio(Long idCarro, Long idAcessorio) {
		carroDAO.removeAcessorio(idCarro, idAcessorio);
	}

}
