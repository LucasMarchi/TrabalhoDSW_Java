package br.ifsp.trabalhodsw.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ifsp.trabalhodsw.entities.Projeto;
import br.ifsp.trabalhodsw.entities.Voluntario;
import br.ifsp.trabalhodsw.repository.Repository;

@ManagedBean
@SessionScoped
public class ProjetoBean {

	private Projeto projeto = new Projeto();
	private List<Voluntario> voluntarios = new ArrayList<Voluntario>();
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	public String gravar() {

		if (projeto.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Projeto deve conter um nome"));
			projeto = new Projeto();
			return "cadastro_projeto";
		}

		if (projeto.getId() != null && projeto.getId().intValue() > 0) {
			new Repository<Projeto>(Projeto.class).atualiza(this.projeto);
			projeto = new Projeto();
			return "lista_projeto?faces-redirect=true";
		} else {
			new Repository<Projeto>(Projeto.class).adiciona(this.projeto);
			projeto = new Projeto();
			return "lista_projeto?faces-redirect=true";
		}

	}

	public List<Projeto> getListaProjetos() {
		return new Repository<Projeto>(Projeto.class).listaTodos();
	}

	public String adicionarVoluntario(Projeto projeto) {
		this.projeto = projeto;
		return "adicionar_voluntarios";
	}

	public void gravarVoluntario(Voluntario voluntario) {
		projeto.getVoluntarios().add(voluntario);
		new Repository<Projeto>(Projeto.class).atualiza(projeto);
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Adicionado com sucesso!!"));
	}

	public void pesquisaeVoluntario() {
		voluntarios = new ArrayList<>();
		voluntarios.add((Voluntario) new Repository<Voluntario>(Voluntario.class).buscaPorCpf(cpf));
	}

	public String removerProjeto() {
		projeto = new Repository<Projeto>(Projeto.class).buscaPorId(projeto.getId());
		new Repository<Projeto>(Projeto.class).remove(projeto);
		return null;
	}
	

}
