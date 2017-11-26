package br.ifsp.trabalhodsw.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ifsp.trabalhodsw.entities.Usuario;
import br.ifsp.trabalhodsw.repository.Repository;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar() {
		
		Usuario userEncontrado  = (Usuario)new Repository<Usuario>(Usuario.class).buscaPorEmail(this.usuario.getEmail(), this.usuario.getSenha());
		
		if(userEncontrado != null) {
			HttpSession session = getSession();
			session.setAttribute("usuario", usuario);
			return "cadastro_voluntario?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Usuario ou senha inválidos!"));

		return "login";
	}

	public String deslogar() {
		HttpSession session = getSession();
		session.removeAttribute("usuario");
		return "login";
	}
	
	public String gravar() {

		if (usuario.getEmail().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("Usuario deve conter um email"));
			usuario = new Usuario();
			return "cadastro_usuario";
		}

		if (usuario.getId() != null && usuario.getId().intValue() > 0) {
			new Repository<Usuario>(Usuario.class).atualiza(this.usuario);
			usuario = new Usuario();
			return "lista_projeto?faces-redirect=true";
		} else {
			new Repository<Usuario>(Usuario.class).adiciona(this.usuario);
			usuario = new Usuario();
			return "lista_projeto?faces-redirect=true";
		}

	}
	
	public List<Usuario> getListaUsuarios() {
		return new Repository<Usuario>(Usuario.class).listaTodos();
	}
	
	public String removerUsuario() {
		usuario = new Repository<Usuario>(Usuario.class).buscaPorId(usuario.getId());
		new Repository<Usuario>(Usuario.class).remove(usuario);
		return null;
	}
	
	private HttpSession getSession() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return session;
	}

}
