package br.com.carthur.dao;
import java.util.List;

import javax.persistence.*;

import br.com.carthur.domain.Acessorio;

public class CarroAcessorioDAO implements ICarroAcessorioDAO {
	@Override
	public void cadastrar(Long idAcessorio, Long idCarro) {
	    EntityManagerFactory entityManagerFactory = 
	            Persistence.createEntityManagerFactory("ExemploJPA");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    
	    // Modificação na query para incluir o ID usando a sequence
	    Query query = entityManager.createNativeQuery(
	        "INSERT INTO carro_acessorio (id, id_acessorio_fk, id_carro_fk) " +
	        "VALUES (nextval('sq_carro_acessorio'), ?, ?)"
	    );
	    query.setParameter(1, idAcessorio);
	    query.setParameter(2, idCarro);
	    query.executeUpdate();
	    
	    entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
	}

	@Override
	public void excluir(Long idAcessorio, Long idCarro) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("DELETE FROM carro_acessorio WHERE id_acessorio_fk = ? AND id_carro_fk = ?");
		query.setParameter(1, idAcessorio);
		query.setParameter(2, idCarro);
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}

	@Override
	public List<Acessorio> buscarAcessoriosPorCarroId(Long idCarro) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT a.* FROM acessorio a INNER JOIN carro_acessorio ca ON a.id = ca.id_acessorio_fk WHERE ca.id_carro_fk = ?", Acessorio.class);
		query.setParameter(1, idCarro);
		List<Acessorio> acessorios = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return acessorios;
	}
    
	

}
