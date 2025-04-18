package br.com.carthur.dao;

import br.com.carthur.dao.generic.IGenericDAO;
import br.com.carthur.domain.Acessorio;

public interface IAcessorioDAO extends IGenericDAO<Acessorio, Long> {
	
	// Define any additional methods specific to Acessorio if needed
	// For example:
	// List<Acessorio> findBySomeCriteria(String criteria);

}
