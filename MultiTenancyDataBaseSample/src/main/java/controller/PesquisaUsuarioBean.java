package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Usuario;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Named
@ViewScoped
@ManagedBean
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigoUsuario;
	
	private Usuario usuario;
	
	@Inject
	private Session session;

	public void pesquisar() {
		this.usuario = (Usuario) session.createCriteria(Usuario.class)
						.setFetchMode("grupos", FetchMode.JOIN)
						.add(Restrictions.eq("codigo", this.codigoUsuario))
						.setCacheable(true)
						.uniqueResult();
	}
	
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
