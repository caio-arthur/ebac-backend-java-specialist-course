package br.com.carthur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.carthur.dao.jdbc.ConnectionFactory;
import br.com.carthur.domain.Cliente;

/**
 * @author Caio Arthur
 */

public class ClienteDAO implements IClienteDAO {

	@Override
	public List<Cliente> listar() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Cliente> list = new ArrayList<>();
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectAll();
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				cliente = new Cliente();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String code = rs.getString("CODIGO");
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setCodigo(code);
				list.add(cliente);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_CLIENTE_2");
		return sb.toString();
	}
	
	@Override
	public Cliente consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelect();
			stm = connection.prepareStatement(sql);
			adicionarParametrosSelect(stm, codigo);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				cliente = new Cliente();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String code = rs.getString("CODIGO");
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setCodigo(code);
			}
			return cliente;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlSelect() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("SELECT * FROM TB_CLIENTE_2 ");
	    sb.append("WHERE CODIGO = ?");
	    return sb.toString();
	}
	
	private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
		stm.setString(1, codigo);
	}

	
	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			adicionarParametrosInsert(stm, cliente);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}
	
	private void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setString(1,  cliente.getNome());
		stm.setString(2,  cliente.getCodigo());
	}

	private String getSqlInsert() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("INSERT INTO TB_CLIENTE_2 (ID, NOME, CODIGO) ");
	    sb.append("VALUES (nextval('SQ_CLIENTE_2'), ?, ?)");
	    return sb.toString();
	}
	
	@Override
	public Integer atualizar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			adicionarParametrosUpdate(stm, cliente);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
		
	}
	
	private String getSqlUpdate() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("UPDATE TB_CLIENTE_2 ");
	    sb.append("SET NOME = ?, CODIGO = ? ");
	    sb.append("WHERE ID = ?");
	    return sb.toString();
	}
	
	private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setString(1,  cliente.getNome());
		stm.setString(2,  cliente.getCodigo());
		stm.setLong(3, cliente.getId());
	}

	@Override
	public Integer deletar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlDelete();
			stm = connection.prepareStatement(sql);
			adicionarParametrosDelete(stm, cliente);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}
	
	private String getSqlDelete() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("DELETE FROM TB_CLIENTE_2 ");
	    sb.append("WHERE CODIGO = ?");
	    return sb.toString();
	}
	
	private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
		stm.setString(1, cliente.getCodigo());
	}
	
	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws Exception {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
