package br.com.carthur.dao;

import java.util.Collections;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;

public class CarroDAO extends GenericDAO<Carro, Long> implements ICarroDAO {

	public CarroDAO() {
		super(Carro.class);
	}

	@Override
	public void addAcessorio(Long idCarro, Long idAcessorio) {
		Carro carro = entityManager.find(Carro.class, idCarro);
		Acessorio acessorio = entityManager.find(Acessorio.class, idAcessorio);
		if (carro == null || acessorio == null) {
			throw new EntityNotFoundException("Carro ou Acessório não encontrado");
		}
		carro.addAcessorio(acessorio);
		entityManager.merge(carro);
	}

	@Override
	public List<Acessorio> getAcessoriosByCarroId(Long idCarro) {
		Carro carro = entityManager.find(Carro.class, idCarro);
		if (carro == null) {
			return Collections.emptyList();
		}
		// inicializa a coleção, caso seja LAZY
		carro.getAcessorios().size();
		return carro.getAcessorios();
	}

//	@Override
//	public void removeAcessorio(Long idCarro, Long idAcessorio) {
//		Carro carro = entityManager.find(Carro.class, idCarro);
//		Acessorio acessorio = entityManager.find(Acessorio.class, idAcessorio);
//		if (carro == null || acessorio == null) {
//			throw new EntityNotFoundException("Carro ou Acessório não encontrado");
//		}
//		carro.removeAcessorio(acessorio);
//		entityManager.merge(carro);
//	}
	
	@Override
	public void removeAcessorio(Long idCarro, Long idAcessorio) {
	    String sql = "DELETE FROM carro_acessorio WHERE carro_id = :carroId AND acessorio_id = :acessorioId";
	    entityManager.createNativeQuery(sql)
	        .setParameter("carroId", idCarro)
	        .setParameter("acessorioId", idAcessorio)
	        .executeUpdate();
	}

}
