package com.lr.wsrest.calc.application;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Anuvab
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/calculator-rest",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Calculator.Rest",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	service = Application.class
)
public class CalculatorRESTAppApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	/***
	 * 
	 * @return
	 */
	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}
	
	/***
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	@GET
	@Path("/sum")
	@Produces("text/plain")
	public String getSum(@QueryParam("param1") String param1,
			@QueryParam("param2") String param2) {
		StringBuilder result = new StringBuilder();
		Long sum = Long.parseLong(param1) + Long.parseLong(param2);

		result.append("Result is " + sum);

		return result.toString();
	}
	
	/***
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	@GET
	@Path("/sub")
	@Produces("text/plain")
	public String getSubtraction(@QueryParam("param1") String param1,
			@QueryParam("param2") String param2) {
		StringBuilder result = new StringBuilder();
		Long sub = Long.parseLong(param1) - Long.parseLong(param2);

		result.append("Result is " + sub);

		return result.toString();
	}

}