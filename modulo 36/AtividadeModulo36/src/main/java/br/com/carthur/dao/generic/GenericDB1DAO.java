package br.com.carthur.dao.generic;

import java.io.Serializable;

import br.com.carthur.domain.Persistente;

public abstract class GenericDB1DAO <T extends Persistente, E extends Serializable> extends GenericDAO<T, E> {

	public GenericDB1DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Postgre1");
	}

}
