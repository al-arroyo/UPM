package cliente;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import entidades.InformacionReserva;

public class AdaptadorClienteServicioCrud {

	private final WebTarget target;
	
	public AdaptadorClienteServicioCrud() {
		Client cliente= ClientBuilder.newClient();
		target = cliente.target("http://localhost:8080/ej-serv-crud/rest/");
	}
	
	public InformacionReserva crearReserva(String nombre, String habitacion) throws Exception {
		InformacionReserva info=null;
		WebTarget target = this.target.path("reserva");
		target=target.queryParam("nombre", nombre);
		target=target.queryParam("habitacion", habitacion);
		Builder peticion = target.request();
		peticion= peticion.accept(MediaType.APPLICATION_XML);
		Response respuesta = peticion.post(null);
		if(respuesta.getStatus()==200) {
			info = respuesta.readEntity(InformacionReserva.class);
			//EXAMEN POSIBLES PREUNTAS
			//especificacion soa Xml
			//¿QUE SIGNFICA HATEOAS¡
			
		}else {
			String causa = respuesta.readEntity(String.class);
			throw new Exception(causa);
			
		}
		return info;
	}
	
	
}

