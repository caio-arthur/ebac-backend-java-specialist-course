package br.com.carthur.domain;


import javax.persistence.*;

@Entity
@Table(name = "carro_acessorio")
public class CarroAcessorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_acessorio_seq")
	@SequenceGenerator(name = "carro_acessorio_seq", sequenceName = "sq_carro_acessorio", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_carro_fk",
			foreignKey = @ForeignKey(name = "fk_carro_acessorio_carro"),
			referencedColumnName = "id", nullable = false)
	private Carro carro;
	
	@ManyToOne
	@JoinColumn(name = "id_acessorio_fk",
			foreignKey = @ForeignKey(name = "fk_carro_acessorio_acessorio"),
			referencedColumnName = "id", nullable = false)
	private Acessorio acessorio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

}
