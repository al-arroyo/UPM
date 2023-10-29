// SOF_COM 23/24 DMC DTE.
// Este es un ejemplo desarrollado en clase que debe ser considerado como tal.
// Puede tener errores y/o estar incompleto.

package calculadoraRMI;

public class CalculadoraExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public CalculadoraExcepcion(String message) {
		super(message);
	}

}
