package br.com.carthur.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque, String> implements IEstoqueDAO {

	
	public EstoqueDAO() {
		super();
	}
	
	@Override
	public Class<Estoque> getTipoClasse() {
		return Estoque.class;
	}

	@Override
	public void atualizarDados(Estoque entity, Estoque entityCadastrado) {
		entityCadastrado.setQuantidade(entity.getQuantidade());
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_ESTOQUE ");
		sb.append("(ID, ID_PRODUTO_FK, QUANTIDADE, CODIGO) ");
		sb.append("VALUES (nextval('sq_estoque'), ?, ?, ?) ");
		return sb.toString();
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_ESTOQUE WHERE CODIGO = ? ";
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_ESTOQUE ");
		sb.append("SET QUANTIDADE = ? ");
		sb.append("WHERE CODIGO = ? ");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque entity) throws SQLException {
		stmInsert.setLong(1, entity.getIdProduto());
		stmInsert.setInt(2, entity.getQuantidade());
		stmInsert.setString(3, entity.getCodigo());
		
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmDelete, String valor) throws SQLException {
		stmDelete.setString(1, valor);
		
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque entity) throws SQLException {
		stmUpdate.setInt(1, entity.getQuantidade());
		stmUpdate.setLong(2, entity.getIdProduto());
		stmUpdate.setString(3, entity.getCodigo());
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, String valor) throws SQLException {
	 	stmSelect.setString(1, valor);
	}


}
