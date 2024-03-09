package br.com.carthur.domain;

import java.util.Objects;

/**
*
* @author caio.arthur
*/
public class Cliente {
	    
	    private String nome;
	    private String cpf;
	    private String tel;
	    private String endereco;
	    private Integer numero;
	    private String cidade;
	    private String estado;

	    public Cliente(String nome, String cpf, String tel, String end, String num, String cidade, String estado) {
	        this.nome = nome;
	        this.cpf = cpf;
	        this.tel = tel;
	        this.endereco = end;
	        this.numero = Integer.valueOf(num.trim());
	        this.cidade = cidade;
	        this.estado = estado;
	    }


		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
		    return cpf;
		}

		public void setCpf(String cpf) {
		    this.cpf = cpf;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getEnd() {
			return endereco;
		}

		public void setEnd(String end) {
			this.endereco = end;
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

		@Override
		public int hashCode() {
		    int hash = 7;
		    hash = 67 * hash + Objects.hashCode(this.cpf);
		    return hash;
		}

		@Override
		public boolean equals(Object obj) {
		    if (this == obj) {
		        return true;
		    }
		    if (obj == null || getClass() != obj.getClass()) {
		        return false;
		    }
		    Cliente other = (Cliente) obj;
		    return Objects.equals(this.cpf, other.cpf);
		}


		@Override
		public String toString() {
		    return
		            "\n  Nome: " + nome +
		            "\n  CPF: " + cpf +
		            "\n  Telefone: " + tel +
		            "\n  Endereço: " + endereco +
		            "\n  Número: " + numero +
		            "\n  Cidade: " + cidade +
		            "\n  Estado: " + estado;
		}

}
	

