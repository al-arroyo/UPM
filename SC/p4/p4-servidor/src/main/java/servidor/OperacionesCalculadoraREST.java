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
import entidades.Resultado;

@Path("/calculadora")
public class OperacionesCalculadoraREST {

	//Path -> http://localhost:8080/p4-servidor/rest/calculadora/

	private OperacionesCalculadora operaciones = new OperacionesCalculadora();
	
	@GET
	@Path("operaciones")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getOperaciones(@QueryParam("numeroBotonesDisponibles") int num) {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		String[] resultado = operaciones.getOperaciones(num);
		result.setListaOperaciones(resultado);
		response = response.entity(result);
		return response.build();
	}
	@GET
	@Path("/operar")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getOperar(@QueryParam("numeroDeOperacion") int num, 
			@QueryParam("operando") double operando) throws Exception {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			double resultado = operaciones.operar(num, operando);
			result.setResultado(resultado);
			response = response.entity(Double.toString(resultado));			
		}catch(Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.PRECONDITION_FAILED);
			response = response.entity(e.getMessage());
		}
		return response.build();
	}	
	@GET
	@Path("/sumar")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getSumar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionSumar(operando1, operando2);
		result.setResultado(resultado);
		response = response.entity(result);
		return response.build();
	}
	@GET
	@Path("/restar")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getRestar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionRestar(operando1, operando2);
		result.setResultado(resultado);
		response = response.entity(result);
		return response.build();
	}
	@GET
	@Path("/multiplicar")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getMultiplicar(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionMultiplicar(operando1, operando2);
		result.setResultado(resultado);
		response = response.entity(result);
		return response.build();
	}
	@GET
	@Path("/dividir")
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_PLAIN})
	public Response getDividir(@QueryParam("operando1") double operando1, 
			@QueryParam("operando2") double operando2) throws Exception {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			double resultado = operaciones.implementacionDividir(operando1, operando2);
			result.setResultado(resultado);
			response = response.entity(result);			
		}catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.PRECONDITION_FAILED);
			response = response.entity(e.getMessage());
		}
		return response.build();
	}
	@GET
	@Path("/ur")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getUR() {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionUR();
		result.setResultado(resultado);
		response = response.entity(result);
		return response.build();
	}
	@GET
	@Path("/memoria")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getMO() {
		Resultado result = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		double resultado = operaciones.implementacionMO();
		result.setResultado(resultado);
		response = response.entity(result);
		return response.build();
	}
	@HEAD
	@Path("/memoria")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getM() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		operaciones.implementacionMA();
		return response.build();

	}
	@DELETE
	@Path("/memoria")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response getML() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		operaciones.implementacionML();
		return response.build();
	}
}
