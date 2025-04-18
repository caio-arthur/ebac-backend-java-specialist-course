package br.com.carthur.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * @author caio.arthur
 *
 */

@Entity
@Table(name = "marca")
public class Marca implements Persistente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_seq")
	@SequenceGenerator(name = "marca_seq", sequenceName = "sq_marca", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "pais_origem", length = 50, nullable = false)
	private String paisOrigem;
	
	@Column(name = "ano_fundacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date anoFundacao;
	
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@OneToMany(mappedBy = "marca")
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

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Date getAnoFundacao() {
		return anoFundacao;
	}

	public void setAnoFundacao(Date anoFundacao) {
		this.anoFundacao = anoFundacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
}
