package br.com.carthur.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.carthur.dao.IAcessorioDAO;
import br.com.carthur.domain.Acessorio;
import br.com.carthur.services.generic.GenericService;

@Stateless
public class AcessorioService extends GenericService<Acessorio, Long> implements IAcessorioService {
	
	private IAcessorioDAO acessorioDAO;
	
	@Inject
	public AcessorioService(IAcessorioDAO acessorioDAO) {
		super(acessorioDAO);
		this.acessorioDAO = acessorioDAO;
	}

}
