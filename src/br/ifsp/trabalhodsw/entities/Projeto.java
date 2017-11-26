package br.ifsp.trabalhodsw.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Projeto {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private Calendar dataInicio = Calendar.getInstance();;
	private Calendar dataTermino = Calendar.getInstance();;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Voluntario> voluntarios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendar getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}
	public List<Voluntario> getVoluntarios() {
		if(voluntarios == null) {
			return new ArrayList<Voluntario>();
		}
		return voluntarios;
	}
	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}
	
}
