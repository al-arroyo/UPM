package servidor;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
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
	@Path("operaciones")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getOperaciones(@QueryParam("numeroBotonesDisponibles") int num) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		String[] resultado = operaciones.getOperaciones(num);
		response = response.entity((resultado));
		return response.build();
	}
	@GET
	@Path("/operar")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getOperar(@QueryParam("numeroDeOperacion") int num, 
			@QueryParam("operando") double operando) throws Exception {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.operar(num, operando);
		response = response.entity(Double.toString(resultado));
		return response.build();
	}	
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
	@GET
	@Path("/restar")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getRestar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionRestar(operando1, operando2);
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
	@GET
	@Path("/multiplicar")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getMultiplicar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionMultiplicar(operando1, operando2);
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
	@GET
	@Path("/dividir")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getDividir(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) throws Exception {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionDividir(operando1, operando2);
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
	@GET
	@Path("/ur")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getUR() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionUR();
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
	@GET
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getMO() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionMO();
		response = response.entity(Double.toString(resultado));
		return response.build();
	}
	@HEAD
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public void getM() {
		//ResponseBuilder response = Response.status(Response.Status.OK);
		operaciones.implementacionMA();

	}
	@DELETE
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public void getML() {
		//ResponseBuilder response = Response.status(Response.Status.OK);
		operaciones.implementacionML();
	}
}
