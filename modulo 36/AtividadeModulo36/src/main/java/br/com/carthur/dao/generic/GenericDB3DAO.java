package br.com.carthur.dao.generic;

import java.io.Serializable;

import br.com.carthur.domain.Persistente;

public class GenericDB3DAO <T extends Persistente, E extends Serializable> extends GenericDAO<T, E> {
	
	public GenericDB3DAO(Class<T> persistenteClass) {
		super(persistenteClass, "Mysql1");
	}

}
