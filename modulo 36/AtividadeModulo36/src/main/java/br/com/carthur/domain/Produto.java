package br.com.carthur.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Persistente {
	
	public List<ProdutoQuantidade> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoQuantidade> produtos) {
		this.produtos = produtos;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "sq_produto", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "codigo", nullable = false, unique = true, length = 36)
	private String codigo;
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	@Column(name = "categoria", nullable = true, length = 50)
	private String categoria;
	@Column(name = "descricao", nullable = true, length = 200)
	private String descricao;
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ProdutoQuantidade> produtos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
