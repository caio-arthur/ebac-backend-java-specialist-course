package br.com.carthur.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.carthur.dao.jdbc.ConnectionFactory;
import br.com.carthur.domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public List<Produto> listar() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto> list = new ArrayList<>();
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectAll();
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				produto = new Produto();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String code = rs.getString("CODIGO");
				String descricao = rs.getString("DESCRICAO");
				BigDecimal valor = rs.getBigDecimal("VALOR");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(code);
				produto.setDescricao(descricao);
				produto.setValor(valor);
				list.add(produto);
			}
			return list;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM TB_PRODUTO");
		return sb.toString();
	}

	@Override
	public Produto consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectByCode();
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				produto = new Produto();
				Long id = rs.getLong("ID");
				String nome = rs.getString("NOME");
				String code = rs.getString("CODIGO");
				String descricao = rs.getString("DESCRICAO");
				BigDecimal valor = rs.getBigDecimal("VALOR");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(code);
				produto.setDescricao(descricao);
				produto.setValor(valor);
			}
			return produto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlSelectByCode() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("SELECT * FROM TB_PRODUTO WHERE CODIGO = ?");
	    return sb.toString();
	}

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			adicionarParametrosInsert(stm, produto);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlInsert() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("INSERT INTO TB_PRODUTO (ID, CODIGO, NOME, DESCRICAO, VALOR) ");
	    sb.append("VALUES (nextval('SQ_PRODUTO'), ?, ?, ?, ?)");
	    return sb.toString();
	}
	
	private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws Exception {
		stm.setString(1, produto.getCodigo());
		stm.setString(2, produto.getNome());
		stm.setString(3, produto.getDescricao());
		stm.setBigDecimal(4, produto.getValor());
	}

	@Override
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			adicionarParametrosUpdate(stm, produto);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlUpdate() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("UPDATE TB_PRODUTO SET CODIGO = ?, NOME = ?, DESCRICAO = ?, VALOR = ? ");
	    sb.append("WHERE ID = ?");
	    return sb.toString();
	}
	
	private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws Exception {
		stm.setString(1, produto.getCodigo());
		stm.setString(2, produto.getNome());
		stm.setString(3, produto.getDescricao());
		stm.setBigDecimal(4, produto.getValor());
		stm.setLong(5, produto.getId());
	}

	@Override
	public Integer deletar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlDelete();
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			return stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String getSqlDelete() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("DELETE FROM TB_PRODUTO WHERE CODIGO = ?");
	    return sb.toString();
	}

}
