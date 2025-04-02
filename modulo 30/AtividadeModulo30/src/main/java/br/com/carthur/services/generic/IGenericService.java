package br.com.carthur.services.generic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import br.com.carthur.domain.Persistente;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TableException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

/**
 * @author caio.arthur
 */

public interface IGenericService <T extends Persistente, E extends Serializable> {
	
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void excluir(E valor) throws DAOException, SQLException;
	
	public void alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException, SQLException;
	
	public T consultar(E valor) throws DAOException, SQLException;
	
	public Collection<T> buscarTodos() throws DAOException, TableException, SQLException;
}
