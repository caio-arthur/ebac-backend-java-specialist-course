package br.com.carthur.dao;

import java.sql.SQLException;

import javax.persistence.Query;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Venda;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class VendaExclusaoDAO extends GenericDAO<Venda, Long> implements IVendaExclusaoDAO {
	
	public VendaExclusaoDAO() {
		super(Venda.class, null);
	}
	
	@Override
	public void excluir(Venda venda) throws TipoChaveNaoEncontradaException, DAOException, SQLException {
	    try {
	        openConnection();
	        excluirProdutosDeVenda(venda.getId());
	        excluirVenda(venda);
	        em.getTransaction().commit();
	    } catch (DAOException | SQLException e) {
	        em.getTransaction().rollback();
	        throw new DAOException("Erro ao excluir a venda: " + e.getMessage(), e);
	    } finally {
	        closeConnection();
	    }
	}

	private void excluirProdutosDeVenda(Long vendaId) throws DAOException {
		try {
			Query query = em.createQuery("DELETE FROM ProdutoQuantidade pq WHERE pq.venda.id = :vendaId");
			query.setParameter("vendaId", vendaId);
			query.executeUpdate();
	    } catch (Exception e) {
	    	throw new DAOException("Erro ao excluir os produtos da venda: " + e.getMessage(), e);
	    }
	}

	private void excluirVenda(Venda entity) throws SQLException {
		try {
			Query query = em.createQuery("DELETE FROM Venda v WHERE v.id = :id");
		    query.setParameter("id", ((Venda) entity).getId());
		    query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Erro ao excluir a venda: " + e.getMessage(), e);
		}
	}
	
}
