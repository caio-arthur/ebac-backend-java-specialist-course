/**
 * @author caio.arthur
 */
package br.com.carthur.services.generic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import br.com.carthur.domain.Persistente;
import br.com.carthur.dao.generic.IGenericDAO;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.MaisDeUmRegistroException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

/**
 * 
 * @param <T> Tipo da entidade persistente
 * @param <E> Tipo da chave da entidade
 */
public abstract class GenericService<T extends Persistente, E extends Serializable> 
implements IGenericService<T, E> {

protected IGenericDAO<T,E> dao;

public GenericService(IGenericDAO<T,E> dao) {
	this.dao = dao;
}

@Override
public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
	return this.dao.cadastrar(entity);
}

@Override
public void excluir(E valor) throws DAOException, SQLException {
	this.dao.excluir(valor);
}

@Override
public void alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException, SQLException {
	this.dao.alterar(entity);
}

@Override
public T consultar(E valor) throws DAOException, SQLException {
	try {
		return this.dao.consultar(valor);
	} catch (MaisDeUmRegistroException | TableException e) {
		// TODO Auto-generated catch block
		//TODO levantar um erro genérico
		e.printStackTrace();
	}
	return null;
}

@Override
public Collection<T> buscarTodos() throws DAOException, TableException, SQLException {
	return this.dao.buscarTodos();
}

}