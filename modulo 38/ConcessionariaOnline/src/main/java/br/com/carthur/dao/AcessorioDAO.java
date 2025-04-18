package br.com.carthur.dao;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Acessorio;

public class AcessorioDAO extends GenericDAO<Acessorio, Long> implements IAcessorioDAO {

	public AcessorioDAO() {
		super(Acessorio.class);
	}
	
//	@Override
//	public Acessorio cadastrar(Acessorio acessorio) {
//		EntityManagerFactory entityManagerFactory = 
//				Persistence.createEntityManagerFactory("ExemploJPA");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(acessorio);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();
//		return acessorio;
//	}
//
//	@Override
//	public void excluir(Acessorio acessorio) {
//		EntityManagerFactory entityManagerFactory = 
//				Persistence.createEntityManagerFactory("ExemploJPA");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.remove(acessorio);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();
//	}
//
//	@Override
//	public Acessorio buscarPorId(Long id) {
//		EntityManagerFactory entityManagerFactory = 
//				Persistence.createEntityManagerFactory("ExemploJPA");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		Acessorio acessorio = entityManager.find(Acessorio.class, id);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();
//		return acessorio;
//	}
	
	
}
