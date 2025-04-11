package br.com.carthur.dao;

import javax.persistence.Query;

import br.com.carthur.dao.generic.GenericDB2DAO;
import br.com.carthur.domain.Cliente;

public class ClienteDB2DAO extends GenericDB2DAO<Cliente, Long> implements IClienteDAO {
	
	public ClienteDB2DAO() {
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
		openConnection();
		Query query = em.createQuery("DELETE FROM Venda v WHERE v.cliente = :cliente");
		query.setParameter("cliente", cliente);
		query.executeUpdate();
		closeConnection();
	}

}
