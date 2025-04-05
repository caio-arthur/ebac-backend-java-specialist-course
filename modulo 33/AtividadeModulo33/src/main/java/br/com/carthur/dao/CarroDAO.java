package br.com.carthur.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.carthur.domain.Acessorio;
import br.com.carthur.domain.Carro;
import javax.persistence.*;

public class CarroDAO implements ICarroDAO {
	
	@Override
	public Carro buscarPorId(Long id) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Carro carro = entityManager.find(Carro.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return carro;
	}

	@Override
	public List<Carro> buscarTodos() {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT c FROM Carro c");
		List<Carro> carros = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return carros;
	}

	@Override
	public Carro cadastrar(Carro carro) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJPA");
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();

	        // Se os acessórios ainda não estiverem persistidos, persista cada um
	        List<Acessorio> acessorios = carro.getAcessorios();
	        if (acessorios != null) {
	            // Criamos uma nova lista para garantir que os objetos estão gerenciados
	            List<Acessorio> acessoriosPersistidos = new ArrayList<>();
	            for (Acessorio acessorio : acessorios) {
	                if (acessorio.getId() == null) {
	                    // Se o acessorio estiver no estado transient, persiste-o
	                    em.persist(acessorio);
	                    acessoriosPersistidos.add(acessorio);
	                } else {
	                    // Se já estiver persistido, utiliza merge para reatachá-lo
	                    acessoriosPersistidos.add(em.merge(acessorio));
	                }
	            }
	            // Atualiza a lista de acessórios do carro com os objetos gerenciados
	            carro.setAcessorios(acessoriosPersistidos);
	        }

	        // Persiste o carro. O JPA cuidará de inserir os registros na tabela de relacionamento
	        em.persist(carro);

	        em.getTransaction().commit();
	        return carro;
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        throw e;
	    } finally {
	        em.close();
	        emf.close();
	    }
	}

	@Override
	public Carro alterar(Carro entity) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return entity;
	}

	@Override
	public void excluir(Carro entity) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
