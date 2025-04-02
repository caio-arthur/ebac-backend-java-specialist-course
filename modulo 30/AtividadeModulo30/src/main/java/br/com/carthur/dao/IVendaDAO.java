package br.com.carthur.dao;

import java.sql.SQLException;

import br.com.carthur.dao.generic.IGenericDAO;
import br.com.carthur.domain.Venda;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException, SQLException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}