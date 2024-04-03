package br.com.carthur.services;

import br.com.carthur.dao.IProdutoDAO;
import br.com.carthur.domain.Produto;
import br.com.carthur.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

	@Override
	public Produto buscarPorCodigo(String codigo) {
		return this.dao.consultar(codigo);
	}

}