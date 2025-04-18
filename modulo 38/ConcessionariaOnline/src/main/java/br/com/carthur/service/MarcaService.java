package br.com.carthur.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.carthur.dao.IMarcaDAO;
import br.com.carthur.domain.Marca;
import br.com.carthur.services.generic.GenericService;

@Stateless
public class MarcaService extends GenericService<Marca, Long> implements IMarcaService {

	private IMarcaDAO marcaDAO;
	
	@Inject
	public MarcaService(IMarcaDAO marcaDAO) {
		super(marcaDAO);
		this.marcaDAO = marcaDAO;
	}
}
