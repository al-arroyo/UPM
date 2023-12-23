package cliente;

import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import CalculadoraGUI.ICalculadora;
import entidades.Resultado;


/**
 * Esta clase sirve para adaptar la interfaz de la clase
 * calculadora.OperacionesCalculadora a la interfaz de
 * CalculadoraGUI.ICalculadora. Se peude utilizar un adaptador de clase o de
 * objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	// private ServicioCalculadora.Iface stubCliente; //Clases creadas con el thrift

	public WebTarget target;

	public AdaptadorOperacionesCalculadoraGUI() {
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/p4-servidor/rest/calculadora/");
	}

	// Escribir los métodos.
	
	public double sumar(double operando1, double operando2){
		Resultado resultado = null;
		Builder peticion = target.path("sumar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double restar(double operando1, double operando2) {
		Resultado resultado = null;
		Builder peticion = target.path("restar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double multiplicar(double operando1, double operando2) {
		Resultado resultado = null;
		Builder peticion = target.path("multiplicar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double dividir(double operando1, double operando2) throws Exception  {
		Resultado resultado = null;
		Builder peticion = target.path("dividir")
					.queryParam("operando1", operando1)
					.queryParam("operando2", operando2)
					.request()
					.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		if(response.getStatusInfo() == Status.PRECONDITION_FAILED)
			throw new Exception(response.readEntity(String.class));
		resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double obtenerUltimoResultado() {
		Resultado resultado = null;
		Builder peticion = target.path("ur")
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public void memoriaLimpiar() {
		Resultado resultado = null;
		Builder peticion = target.path("memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);

	}

	public void memoriaAniadir() {
		Resultado resultado = null;
		Builder peticion = target.path("memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
	}

	public double memoriaObtener() {
		Resultado resultado = null;
		Builder peticion = target.path("memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado=	response.readEntity(Resultado.class);
		return resultado.getResultado();
	}


	public double operar(int numeroDeOperacion, double operando) throws Exception {
		Resultado resultado = null;
		Builder peticion = target.path("operar")
					.queryParam("numeroDeOperacion", numeroDeOperacion)
					.queryParam("operando", operando)
					.request()
					.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		if(response.getStatusInfo() == Status.PRECONDITION_FAILED)
			throw new Exception(response.readEntity(String.class));
		resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public String[] getOperaciones(int numeroBotonesDisponibles) {
		Resultado resultado = null;
		Builder peticion = target.path("operaciones")
					.queryParam("numeroBotonesDisponibles", numeroBotonesDisponibles)
					.request()
					.accept(MediaType.APPLICATION_XML);
		Response response = peticion.get();
		resultado = response.readEntity(Resultado.class);
		return resultado.getListaOperaciones();
		
	}

}
