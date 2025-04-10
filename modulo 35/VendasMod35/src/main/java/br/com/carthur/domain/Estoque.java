package br.com.carthur.domain;

import javax.persistence.*;

@Entity
@Table(name = "ESTOQUE")
public class Estoque implements Persistente {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_produto_fk", 
				foreignKey = @ForeignKey(name = "fk_estoque_produto"),
				referencedColumnName = "id", nullable = false)
	private Produto produto;
	
	@Column(name = "codigo", nullable = false, unique = true, length = 20)
	private String codigo;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
