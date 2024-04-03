/**
 * @author caio.arthur
 */
package br.com.carthur.services.generic;

import java.io.Serializable;
import java.util.Collection;
import br.com.carthur.domain.Persistente;
import br.com.carthur.dao.generic.IGenericDAO;
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
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(E valor) {
		this.dao.excluir(valor);
	}

	@Override
	public void alterar(T entity) throws TipoChaveNaoEncontradaException {
		this.dao.alterar(entity);
	}

	@Override
	public T consultar(E valor) {
		return this.dao.consultar(valor);
	}

	@Override
	public Collection<T> buscarTodos() {
		return this.dao.buscarTodos();
	}
}
