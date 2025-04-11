package br.com.carthur.domain;

import java.util.List;

import javax.persistence.*;

/**
 * @author caio.arthur
 */

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Persistente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(name = "cliente_seq", sequenceName = "sq_cliente", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Venda> vendas;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "idade", nullable = false)
	private Integer idade;
	
	@Column(name = "cpf", nullable = false, unique = true)
	private Long cpf;
	
	@Column(name = "telefone", nullable = false)
	private Long telefone;
	@Column(name = "logradouro", nullable = true, length = 200)
	private String logradouro;
	@Column(name = "numero", nullable = true)
	private Integer numero;
	@Column(name = "cidade", nullable = true, length = 100)
	private String cidade;
	@Column(name = "estado", nullable = true, length = 20)
	private String estado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	

}
