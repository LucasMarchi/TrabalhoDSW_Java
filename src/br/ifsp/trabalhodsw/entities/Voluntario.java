package br.ifsp.trabalhodsw.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Voluntario {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column (name="cpf", nullable=false)
	String cpf;

	@Column (name="email", nullable=false)
	String email;
	
	@Column (name="nome", nullable=false)
	String nome;
	
	@Column (name="telefone", nullable=false)
	String telefone;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

}