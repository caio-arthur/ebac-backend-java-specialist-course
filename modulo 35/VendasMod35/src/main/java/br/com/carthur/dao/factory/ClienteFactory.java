package br.com.carthur.dao.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.carthur.domain.Cliente;

public class ClienteFactory {

	public static Cliente convert(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("ID"));
		cliente.setNome(rs.getString(("NOME")));
		cliente.setCpf(rs.getLong(("CPF")));
		cliente.setTelefone(rs.getLong(("TELEFONE")));
		cliente.setLogradouro(rs.getString(("LOGRADOURO")));
		cliente.setNumero(rs.getInt(("NUMERO")));
		cliente.setCidade(rs.getString(("CIDADE")));
		cliente.setEstado(rs.getString(("ESTADO")));
		return cliente;
	}
}