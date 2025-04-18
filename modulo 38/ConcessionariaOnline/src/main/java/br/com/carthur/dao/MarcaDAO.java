package br.com.carthur.dao;

import br.com.carthur.dao.generic.GenericDAO;

import br.com.carthur.domain.Marca;

public class MarcaDAO extends GenericDAO<Marca, Long> implements IMarcaDAO {
	
	public MarcaDAO() {
		super(Marca.class);
	}

}
