package util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@ApplicationScoped
public class HibernateSessionProducer {

	private SessionFactory sessionFactory;

	@Inject
	@TenantInject
	private Tenant tenant;

	public HibernateSessionProducer() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("gerenciadorAcessoPU");
		this.sessionFactory = factory.unwrap(SessionFactory.class);
		System.out
				.println("++++++++++++++++++++++++++++ Passou  HibernateSessionProducer");
	}

	@Produces
	@RequestScoped
	public Session create() {
		System.out
				.println("++++++++++++++++++++++++++++ Passou Session create");
		return sessionFactory.withOptions().tenantIdentifier(tenant.getId())
				.openSession();
	}

	public void close(@Disposes Session session) {
		session.close();
	}

}
