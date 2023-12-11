// SOF_COM 23/24 DMC DTE.
// Esta clase debe añadirse al paquete servidor.

package servidor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application {

	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(OperacionesCalculadoraREST.class));
	}
}