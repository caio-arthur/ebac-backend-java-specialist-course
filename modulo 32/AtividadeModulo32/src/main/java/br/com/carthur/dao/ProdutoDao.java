package br.com.carthur.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.carthur.domain.Produto;

public class ProdutoDao implements IProdutoDao {
	
	@Override
	public Produto cadastrar(Produto produto) {
		
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		 
		entityManager.close();
		entityManagerFactory.close();
		
		
		return produto;
	}

	@Override
	public void excluir(Produto produto) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		produto = entityManager.merge(produto);
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		 
		entityManager.close();
		entityManagerFactory.close();
		
	}


	@Override
	public Produto buscarPorId(Long id) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("ExemploJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return produto;
	}

	
}
