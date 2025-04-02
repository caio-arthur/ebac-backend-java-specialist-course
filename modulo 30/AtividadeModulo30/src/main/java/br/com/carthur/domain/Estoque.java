package br.com.carthur.domain;

import br.com.carthur.annotation.ColunaTabela;
import br.com.carthur.annotation.Tabela;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente {
	
	@ColunaTabela(dbName= "id", setJavaName = "setId")
	private Long id;
	
	@ColunaTabela(dbName= "id_produto_fk", setJavaName = "setIdProduto")
	private Long idProduto;
	
	@ColunaTabela(dbName= "codigo", setJavaName = "setCodigo")
	private String codigo;
	
	@ColunaTabela(dbName= "quantidade", setJavaName = "setQuantidade")
	private Integer quantidade;
	
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
