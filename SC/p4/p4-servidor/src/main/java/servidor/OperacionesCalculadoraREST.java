package servidor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import calculadora.OperacionesCalculadora;

@Path("/calculadora")
public class OperacionesCalculadoraREST {

	private OperacionesCalculadora operaciones = new OperacionesCalculadora();
	
	@GET
	@Path("/sumar")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getSumar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionSumar(operando1, operando2);
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
}
