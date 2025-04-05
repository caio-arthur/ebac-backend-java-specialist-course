package br.com.carthur.domain;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "carro")
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
	@SequenceGenerator(name = "carro_seq", sequenceName = "sq_carro", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "modelo", length = 50, nullable = false)
	private String modelo;
	
	@Column(name = "ano_fabricacao", nullable = false)
	private Instant anoFabricacao;
	
	@Column(name = "cor", length = 20, nullable = false)
	private String cor;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "id_marca_fk",
			foreignKey = @ForeignKey(name = "fk_carro_marca"),
			referencedColumnName = "id", nullable = false)
	private Marca marca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Instant getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Instant anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	
}
