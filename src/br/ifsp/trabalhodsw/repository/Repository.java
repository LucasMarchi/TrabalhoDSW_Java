package br.ifsp.trabalhodsw.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class Repository<T> {
	
	private final Class<T> classe;

	public Repository(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}
	
	public Object buscaPorCpf(String cpf) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("select v FROM Voluntario v WHERE v.cpf = :cpf").setParameter("cpf", cpf);
		Object object = query.getResultList().get(0);
		em.close();
		return object;
	}
	
	public Object buscaPorEmail(String email, String senha) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("select u FROM Usuario u WHERE u.email = :email and u.senha = :senha").setParameter("email", email).setParameter("senha", senha);
		Object object = null;
		if(query != null && query.getResultList() != null && query.getResultList().size() > 0) {
			 object = query.getResultList().get(0);
		}
		
		em.close();
		return object;
	}

	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from livro n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}
	

}
