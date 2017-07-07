package util.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class TenantProducer {

	@Produces
	@RequestScoped
	@TenantInject
	public Tenant create() {
		String requestURL = getRequest().getRequestURL().toString();
		String tenantId = requestURL.substring(7).replaceAll("\\..+", "");
		System.out.println("++++++++++++++++++++++++ Tenant create() "
				+ tenantId);
		return new Tenant(tenantId);
	}

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

}
