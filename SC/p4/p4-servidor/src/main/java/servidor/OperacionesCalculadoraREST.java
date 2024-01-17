package servidor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import calculadora.OperacionesCalculadora;
import entidades.*;

//Path -> http://localhost:8080/p4-servidor/rest/calculadora/


@Path("/calculadora")
public class OperacionesCalculadoraREST {
	@Context
	private HttpServletRequest hsr;
	private OperacionesCalculadora getOperacionesCalculadora() {
		final String ATRIBUTOCALCULADORA = "operaciones";
		final HttpSession sesion = hsr.getSession();
		OperacionesCalculadora operaciones = (OperacionesCalculadora) sesion.getAttribute(ATRIBUTOCALCULADORA);
		if (operaciones == null) {
			operaciones = new OperacionesCalculadora();
			sesion.setAttribute(ATRIBUTOCALCULADORA, operaciones);
		}
		return operaciones;
	}

	@GET
	@Path("/sumar")
	@Produces({MediaType.APPLICATION_XML})
	public Response getSumar(@QueryParam("operando1") double operando1,@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = this.getOperacionesCalculadora().implementacionSumar(operando1, operando2);
		res.setResultado(resultado);
		response = response.entity(res);
		System.out.println();
		return response.build();
	}
	
	@GET
	@Path("/restar")
	@Produces({MediaType.APPLICATION_XML})
	public Response getRestar(@QueryParam("operando1") double operando1,@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = this.getOperacionesCalculadora().implementacionRestar(operando1, operando2);
		res.setResultado(resultado);
		response = response.entity(res);
		System.out.println();
		return response.build();
	}
	
	@GET
	@Path("/multiplicar")
	@Produces({MediaType.APPLICATION_XML})
	public Response getMultiplicar(@QueryParam("operando1") double operando1,@QueryParam("operando2") double operando2) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = this.getOperacionesCalculadora().implementacionMultiplicar(operando1, operando2);
		res.setResultado(resultado);
		response = response.entity(res);
		System.out.println();
		return response.build();
	}
	
	@GET
	@Path("/dividir")
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response getDividir(@QueryParam("dividendo") double dividendo,@QueryParam("divisor") double divisor)  {
		Resultado res = new Resultado();
		ResponseBuilder response = Response.status(Response.Status.OK);
		try {
			double resultado = this.getOperacionesCalculadora().implementacionDividir(dividendo, divisor);
			res.setResultado(resultado); 
			response = response.entity(res); 
			
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.PRECONDITION_FAILED);
			response = response.entity(e.getMessage());
		}
		System.out.println();
		return response.build();
	}
	
	@GET
	@Path("/ur")
	@Produces({MediaType.APPLICATION_XML})
	public Response getUltimoResultado() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = this.getOperacionesCalculadora().implementacionUR();
		res.setResultado(resultado);
		response = response.entity(res);
		System.out.println();
		return response.build();
	}
	
	
	@GET
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_XML})
	public Response getMO() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = this.getOperacionesCalculadora().implementacionMO();
		res.setResultado(resultado);
		response = response.entity(res);
		System.out.println();
		return response.build();
	}
	
	
	@HEAD
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_XML})
	public Response memoriaAniadirRest() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		this.getOperacionesCalculadora().implementacionMA();
		System.out.println();
		return response.build();
	}
	
	@DELETE
	@Path("/memoria")
	@Produces({MediaType.APPLICATION_XML})
	public Response memoriaLimpiar() {
		ResponseBuilder response = Response.status(Response.Status.OK);
		this.getOperacionesCalculadora().implementacionML();
		System.out.println();
		return response.build();
	}
	
	
	@GET
	@Path("/operaciones")
	@Produces({MediaType.APPLICATION_XML})
	public Response operaciones(@QueryParam("numeroOperaciones") int numeroOperaciones) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado botones = new Resultado();
		String[] resultado = this.getOperacionesCalculadora().getOperaciones(numeroOperaciones);
		botones.setListaOperaciones(resultado);
		
	    response.entity(botones);
		System.out.println();
		return response.build();
	}
	
	@GET
	@Path("/operar")
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response operar(@QueryParam("operacion") int operacion,@QueryParam("operando") double operando) {
		ResponseBuilder response = Response.status(Response.Status.OK);
		Resultado res = new Resultado();
		double resultado = 0;
		try {
			resultado = this.getOperacionesCalculadora().operar(operacion, operando);
			res.setResultado(resultado);
			response = response.entity(res);

		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.PRECONDITION_FAILED);
			response = response.entity(e.getMessage());                                                 
		}
		System.out.println();
		return response.build();
	}
	
	
}
