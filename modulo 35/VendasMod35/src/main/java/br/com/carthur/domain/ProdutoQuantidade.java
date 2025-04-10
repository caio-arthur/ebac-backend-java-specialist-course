package br.com.carthur.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "PRODUTO_QUANTIDADE")
public class ProdutoQuantidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_quantidade_seq")
	@SequenceGenerator(name = "produto_quantidade_seq", sequenceName = "sq_produto_quantidade", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_produto_fk", 
				foreignKey = @ForeignKey(name = "fk_produto_quantidade_produto"),
				referencedColumnName = "id", nullable = true)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_venda_fk", 
				foreignKey = @ForeignKey(name = "fk_produto_quantidade_venda"),
				referencedColumnName = "id", nullable = true)
	private Venda venda;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ProdutoQuantidade() {
		this.quantidade = 0;
		this.valorTotal = BigDecimal.ZERO;
	}

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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void adicionar(Integer quantidade) {
		this.quantidade += quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		BigDecimal novoTotal = this.valorTotal.add(novoValor);
		this.valorTotal = novoTotal;
	}
	
	public void remover(Integer quantidade) {
		this.quantidade -= quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.subtract(novoValor);
	}
	
	
}
