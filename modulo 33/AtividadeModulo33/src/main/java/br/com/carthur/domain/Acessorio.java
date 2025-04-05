package br.com.carthur.domain;


import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "acessorio")
public class Acessorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acessorio_seq")
	@SequenceGenerator(name = "acessorio_seq", sequenceName = "sq_acessorio", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@ManyToMany(mappedBy = "acessorios")
	private List<Carro> carros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}


}
