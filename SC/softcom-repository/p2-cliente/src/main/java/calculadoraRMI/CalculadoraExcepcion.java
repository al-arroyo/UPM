package calculadoraRMI;

public class CalculadoraExcepcion extends Exception{
    private static final long serialVersionUID = 1L;

	public CalculadoraExcepcion(String message) {
		super(message);
	}
}
