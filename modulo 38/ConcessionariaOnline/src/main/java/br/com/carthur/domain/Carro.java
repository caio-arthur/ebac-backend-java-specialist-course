package br.com.carthur.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "carro")
public class Carro implements Persistente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
	@SequenceGenerator(name = "carro_seq", sequenceName = "sq_carro", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "modelo", length = 50, nullable = false)
	private String modelo;
	
	@Column(name = "ano_fabricacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date anoFabricacao;
	
	@Column(name = "cor", length = 20, nullable = false)
	private String cor;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "id_marca_fk",
			foreignKey = @ForeignKey(name = "fk_carro_marca"),
			referencedColumnName = "id", nullable = false)
	private Marca marca;
	
	@ManyToMany
	@JoinTable(name = "carro_acessorio",
			joinColumns = @JoinColumn(name = "id_carro_fk", foreignKey = @ForeignKey(name = "fk_carro_acessorio_carro")),
			inverseJoinColumns = @JoinColumn(name = "id_acessorio_fk", foreignKey = @ForeignKey(name = "fk_carro_acessorio_acessorio")))
	private List<Acessorio> acessorios;
	
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

	public Date getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Date anoFabricacao) {
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

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
	
    // método de conveniência para adicionar
    public void addAcessorio(Acessorio acessorio) {
        if (!this.acessorios.contains(acessorio)) {
            this.acessorios.add(acessorio);
            acessorio.getCarros().add(this);
        }
    }

    // método de conveniência para remover
    public void removeAcessorio(Acessorio acessorio) {
		this.getAcessorios().size();
        if (this.acessorios.contains(acessorio)) {
        	this.acessorios.remove(acessorio);
            acessorio.getCarros().remove(this);
        }
    }

}
