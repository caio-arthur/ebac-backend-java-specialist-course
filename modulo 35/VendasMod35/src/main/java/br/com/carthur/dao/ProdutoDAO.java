package br.com.carthur.dao;

import java.sql.SQLException;

import javax.persistence.Query;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Produto;
import br.com.carthur.exception.DAOException;
import br.com.carthur.exception.TipoChaveNaoEncontradaException;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {
	
	public ProdutoDAO() {
		super(Produto.class);
	}
	
	@Override
	public void excluir(Produto produto) throws DAOException, SQLException, TipoChaveNaoEncontradaException {
	    if (produto == null) {
	        throw new DAOException("Produto não pode ser nulo");
	    }
	    
	    try {
	        excluirProdutosDoProduto(produto.getId());
	        super.excluir(produto);
	    } catch (DAOException | SQLException e) {
	        throw new DAOException("Erro ao excluir o produto: " + e.getMessage(), e);
	    }
	}

	public void excluirProdutosDoProduto(Long produtoId) {
	    // Exclui os produtos associados ao produto
		openConnection();
	    Query query = em.createQuery("DELETE FROM ProdutoQuantidade pq WHERE pq.produto.id = :produtoId");
	    query.setParameter("produtoId", produtoId);
	    query.executeUpdate();
		em.getTransaction().commit();
	    closeConnection();
	}
	
}
