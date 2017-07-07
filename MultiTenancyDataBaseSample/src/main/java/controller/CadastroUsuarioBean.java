package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Status;
import model.Usuario;

import org.hibernate.Session;

@Named
@ViewScoped
@ManagedBean
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Session session;

	private Usuario usuario = new Usuario();

	public void salvar() {
		System.out.println(this.usuario);
		System.out
				.println("+++++++++++++++++++++++++++++++CadastroUsuarioBean ");
		try {
			session.getTransaction().begin();
			session.merge(usuario);
			session.getTransaction().commit();
			usuario = new Usuario();
		} catch (Exception e) {
			throw new RuntimeException("Erro salvando usuario", e);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Status> getStatusList() {
		return Arrays.asList(Status.values());
	}

}
