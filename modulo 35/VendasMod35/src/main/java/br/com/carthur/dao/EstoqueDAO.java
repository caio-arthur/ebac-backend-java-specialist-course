package br.com.carthur.dao;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque, String> implements IEstoqueDAO {

	public EstoqueDAO() {
		super(Estoque.class);
	}

}
