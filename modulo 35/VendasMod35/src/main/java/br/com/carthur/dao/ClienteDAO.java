package br.com.carthur.dao;

import javax.persistence.Query;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Cliente;

/**
 * @author caio.arthur
 */
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {
	
	public ClienteDAO() {
		super(Cliente.class);
	}
	
	@Override
	public void excluir(Cliente cliente) {
		try {
			// Excluir as vendas associadas ao cliente
			excluirVendasDeCliente(cliente);
			super.excluir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void excluirVendasDeCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		openConnection();
		Query query = em.createQuery("DELETE FROM Venda v WHERE v.cliente = :cliente");
		query.setParameter("cliente", cliente);
		query.executeUpdate();
		closeConnection();
	}

}
