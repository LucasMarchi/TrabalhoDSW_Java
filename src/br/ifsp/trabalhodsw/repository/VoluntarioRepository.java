package br.ifsp.trabalhodsw.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List <Voluntario> buscaTodas() {
		Query query = this.manager.createQuery(" SELECT e FROM Voluntario e");
		return query.getResultList();
	}

}
