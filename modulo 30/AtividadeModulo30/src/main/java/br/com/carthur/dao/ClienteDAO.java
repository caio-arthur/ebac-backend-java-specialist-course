package br.com.carthur.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.carthur.dao.generic.GenericDAO;
import br.com.carthur.domain.Cliente;

/**
 * @author caio.arthur
 */
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {
	
	public ClienteDAO() {
		super();
	}

	@Override
	public Class<Cliente> getTipoClasse() {
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		entityCadastrado.setCidade(entity.getCidade());
		entityCadastrado.setCpf(entity.getCpf());
		entityCadastrado.setIdade(entity.getIdade());
		entityCadastrado.setEndereco(entity.getEndereco());
		entityCadastrado.setEstado(entity.getEstado());
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setNumero(entity.getNumero());
		entityCadastrado.setTelefone(entity.getTelefone());
		
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_CLIENTE ");
		sql.append("(ID, NOME, IDADE, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO) ");
		sql.append("VALUES (nextval('sq_cliente'), ?, ?, ?, ?, ?, ?, ?, ?)");
		return sql.toString();
	}

	@Override
	protected String getQueryExclusao() {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TB_CLIENTE ");
		sql.append("WHERE CPF = ?");
		return sql.toString();
	}

	@Override
	protected String getQueryAtualizacao() {
	    StringBuilder sql = new StringBuilder();
	    sql.append("UPDATE TB_CLIENTE ");
	    sql.append("SET NOME = ?, IDADE = ?, TEL = ?, ENDERECO = ?, NUMERO = ?, CIDADE = ?, ESTADO = ? ");
	    sql.append("WHERE CPF = ?");
	    return sql.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente entity) throws SQLException {
		stmInsert.setString(1, entity.getNome());
		stmInsert.setInt(2, entity.getIdade());
		stmInsert.setLong(3, entity.getCpf());
		stmInsert.setLong(4, entity.getTelefone());
		stmInsert.setString(5, entity.getEndereco());
		stmInsert.setInt(6, entity.getNumero());
		stmInsert.setString(7, entity.getCidade());
		stmInsert.setString(8, entity.getEstado());
		
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
		stmDelete.setLong(1, valor);
		
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente entity) throws SQLException {
	    stmUpdate.setString(1, entity.getNome());
	    stmUpdate.setInt(2, entity.getIdade());
	    stmUpdate.setLong(3, entity.getTelefone());  
	    stmUpdate.setString(4, entity.getEndereco());
	    stmUpdate.setInt(5, entity.getNumero());
	    stmUpdate.setString(6, entity.getCidade());
	    stmUpdate.setString(7, entity.getEstado());
	    
	    stmUpdate.setLong(8, entity.getCpf()); 
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
		
	}
}
