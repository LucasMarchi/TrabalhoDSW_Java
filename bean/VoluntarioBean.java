package br.ifsp.trabalhodsw.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.trabalhodsw.entities.Voluntario;
import br.ifsp.trabalhodsw.repository.VoluntarioRepository;

@ManagedBean(name="voluntarioBean")
public class VoluntarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
	private int id;
	
	

	public String cadastrarVoluntario(){
		
		Voluntario edit = new Voluntario();
		edit.setNome(nome);
		edit.setEmail(email);
		edit.setCpf(cpf);
		edit.setTelefone(telefone);
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("TrabalhoDSW");
		
		EntityManager manager = factory.createEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		manager.getTransaction().begin();
		
		voluntarioRepository.adiciona(edit);
		
		manager.getTransaction().commit();
		
		factory.close();
		
		System.out.println("Nome: " + nome);
		System.out.println("E-mail: " + email);
		
		
		return "RespostaVoluntario";
	}
	
	public String removerVoluntario(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TrabalhoDSW");
		EntityManager manager = factory.createEntityManager();
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		Voluntario encontrado = voluntarioRepository.busca(id);
		
		manager.getTransaction().begin();
		voluntarioRepository.remove(encontrado);
		manager.getTransaction().commit();		
				
		return null;
	}

	public String atualizarVoluntario(){
		

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TrabalhoDSW");
		EntityManager manager = factory.createEntityManager();
		
		VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
		
		Voluntario edit = new Voluntario();
		edit.setId(id);
		edit.setNome(nome);
		edit.setEmail(email);
		edit.setCpf(cpf);
		edit.setTelefone(telefone);
		
		Voluntario encontrado = voluntarioRepository.busca(4);
		
		manager.getTransaction().begin();
		voluntarioRepository.atualiza(edit);
		manager.getTransaction().commit();	
		
		return null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
