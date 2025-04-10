package br.com.carthur.dao.generic;

import br.com.carthur.domain.*;
import br.com.carthur.exception.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author caio.arthur
 *
 * Classe genérica que implementa interface genérica com os métodos de CRUD
 */
public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E> {

    public GenericDAO(Class<T> persistenteClass) {
		this.persistenteClass = persistenteClass;
    }
    
    protected EntityManagerFactory emf;
    protected EntityManager em;
    
    private Class<T> persistenteClass;
    
    @Override
    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        openConnection();
        em.persist(entity);
        em.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public void excluir(T entity) throws DAOException, SQLException, TipoChaveNaoEncontradaException {
		openConnection();
		entity = em.merge(entity);
		em.remove(entity);
		em.getTransaction().commit();
		closeConnection();
    }

    @Override
    public T alterar(T entity) throws TipoChaveNaoEncontradaException, SQLException, DAOException {
        openConnection();
        entity = em.merge(entity);
        em.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException, SQLException {
        openConnection();
        T entity = em.find(this.persistenteClass, valor);
        em.getTransaction().commit();
        closeConnection();
        return entity;
    }
    
    @Override
    public Collection<T> buscarTodos() throws TableException, DAOException, SQLException {
    	openConnection();
    		List<T> list =
    				em.createQuery(getSelectSql(), this.persistenteClass).getResultList();
    		closeConnection();
    	return list;
    }
    
    private String getSelectSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT obj FROM ");
		sql.append(this.persistenteClass.getSimpleName());
		sql.append(" obj");
		return sql.toString();
	}
    
    protected void openConnection() {
		emf = Persistence.createEntityManagerFactory("ExemploJPA");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
    
    protected void closeConnection() {
    	em.close();
    	emf.close();
    }
  
}