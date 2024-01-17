package cliente;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

	public WebTarget target;
	private Cookie sessionId;
	private final String key = "JSESSIONID";

	public AdaptadorOperacionesCalculadoraGUI() {
		final ClientConfig config = new ClientConfig();
		final Client client = ClientBuilder.newClient(config);
		sessionId = null;
		target = client.target("http://localhost:8080/p4-servidor/rest/calculadora/");
	}

	public double sumar(double operando1, double operando2) {
		Builder peticion = target.path("/sumar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double restar(double operando1, double operando2) {
		Builder peticion = target.path("/restar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;Resultado resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double multiplicar(double operando1, double operando2) {
		Builder peticion = target.path("/multiplicar")
				.queryParam("operando1", operando1)
				.queryParam("operando2", operando2)
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double dividir(double dividendo, double divisor) throws Exception {
		Builder peticion = target.path("/dividir")
				.queryParam("dividendo", dividendo)
				.queryParam("divisor", divisor)
				.request()
				.accept(MediaType.APPLICATION_XML)
				.accept(MediaType.TEXT_PLAIN);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		switch(response.getMediaType().toString()) {
		case "application/xml":
			Resultado resultado = response.readEntity(Resultado.class);
	        return resultado.getResultado();
		case "text/plain":
			throw new Exception(response.readEntity(String.class));
		default:
	        throw new Exception("Formato de respuesta desconocido: " + response.getMediaType().toString());
		}
	}

	public double obtenerUltimoResultado() {
		Builder peticion = target.path("/ur")
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public void memoriaLimpiar() {
		Builder peticion = target.path("/memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.delete();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
	}

	public void memoriaAniadir() {
		Builder peticion = target.path("/memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.head();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
	}

	public double memoriaObtener() {
		Builder peticion = target.path("/memoria")
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado resultado = response.readEntity(Resultado.class);
		return resultado.getResultado();
	}

	public double operar(int numeroDeOperacion, double operando) throws Exception {
		Builder peticion = target.path("/operar")
					.queryParam("operacion", numeroDeOperacion)
					.queryParam("operando", operando)
					.request()
					.accept(MediaType.APPLICATION_XML)
					.accept(MediaType.TEXT_PLAIN);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		switch(response.getMediaType().toString()) {
			case "application/xml":
				Resultado resultado = response.readEntity(Resultado.class);
		        return resultado.getResultado();
			case "text/plain":
				throw new Exception(response.readEntity(String.class));
			default:
		        throw new Exception("Formato de respuesta desconocido: " + response.getMediaType().toString());
		}
	}

	public String[] getOperaciones(int numeroBotonesDisponibles) {
		Builder peticion = target.path("/operaciones")
				.queryParam("numeroOperaciones", numeroBotonesDisponibles)
				.request()
				.accept(MediaType.APPLICATION_XML);
		if(sessionId != null)
			peticion = peticion.cookie(sessionId);
		Response response = peticion.get();
		final Cookie aSessionId = response.getCookies().get(key);
		if(sessionId == null)
			sessionId = aSessionId;
		Resultado res = response.readEntity(Resultado.class);
		return res.getListaOperaciones();
	}

}
