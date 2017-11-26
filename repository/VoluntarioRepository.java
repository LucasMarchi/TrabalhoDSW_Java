package br.ifsp.trabalhodsw.repository;

import javax.persistence.EntityManager;
import br.ifsp.trabalhodsw.entities.Voluntario;

public class VoluntarioRepository {
	
	private EntityManager manager ;
	
	public VoluntarioRepository (EntityManager manager ) {
		this.manager = manager ;
	}
	
	public void adiciona (Voluntario e) {
		this.manager.persist(e);
	}
	
	public void remove(Voluntario e) {
		this.manager.remove(e);
	}
	
	public void atualiza(Voluntario e){
		this.manager.merge(e);
	}
	
	public Voluntario busca (int id) {
		return this.manager.find (Voluntario.class, id);
	}

}
