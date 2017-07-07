package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Status;
import model.Usuario;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

@Named
@ViewScoped
@ManagedBean
public class SiteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Session session;
	
	public Long getTotalUsuarios() {
//		return (Long) session.createQuery("select count(u) from Usuario u")
//				.setCacheable(true)
//				.uniqueResult();
		return (long) 10;
	}
	
	public Long getUsuariosAtivos() {
//		return (Long) session.createCriteria(Usuario.class)
//				.setProjection(Projections.count("codigo"))
//				.add(Restrictions.eq("status", Status.ATIVO))
//				.setCacheable(true)
//				.uniqueResult();
		return (long) 10;
	}
	
}
