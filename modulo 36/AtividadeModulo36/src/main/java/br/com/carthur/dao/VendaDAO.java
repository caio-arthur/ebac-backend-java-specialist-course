package br.com.carthur.dao;

import java.sql.SQLException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Venda;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class VendaDAO extends GenericDAO<Venda, Long> implements IVendaDAO {
	
	public VendaDAO() {
		super(Venda.class, null);
	}
	
	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException, SQLException {
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException, SQLException {
		super.alterar(venda);
	}
	
	@Override
	public void excluir(Venda venda) throws DAOException, SQLException {
		throw new UnsupportedOperationException("Operação não permitida.");
	}
	
	@Override
	public Venda consultarComCollection(Long id) {
		openConnection();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Venda> query = cb.createQuery(Venda.class);
		Root<Venda> root = query.from(Venda.class); 
		root.fetch("cliente");
		root.fetch("produtos");
		query.select(root).where(cb.equal(root.get("id"), id));
		TypedQuery<Venda> typedQuery = em.createQuery(query);
		Venda venda = typedQuery.getSingleResult();
		closeConnection();
		return venda;
	}

}
