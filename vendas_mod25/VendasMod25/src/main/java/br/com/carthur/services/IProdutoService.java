package br.com.carthur.services;

import br.com.carthur.domain.Produto;
import br.com.carthur.services.generic.IGenericService;

public interface IProdutoService extends IGenericService<Produto, String> {
	Produto buscarPorCodigo(String cpf);
}
