package cliente;



import CalculadoraGUI.ICalculadora;
import wsCalculadora.Exception_Exception;
import wsCalculadora.OperacionesCalculadora;
import wsCalculadora.OperacionesCalculadoraService;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora{
	
	private OperacionesCalculadoraService ss;
	private OperacionesCalculadora port;

	public AdaptadorOperacionesCalculadoraGUI(OperacionesCalculadoraService ss, OperacionesCalculadora port)
	{
		this.ss = ss;
		this.port = port;
	}

	/*
	 *	public void memoriaAniadir()
		Añade a la memoria acumuladora el último resultado obtenido al realizar una operación aritmética.
	*/
	@Override
	public void memoriaAniadir() {
        port.implementacionMA();
	}
	/*
	 *	public void memoriaLimpiar()
		Pone a cero la memoria acumuladora de la calculadora.
	 */
	@Override
	public void memoriaLimpiar() {
        port.implementacionML();

	}
	/*
	 *	public double memoriaObtener()
		Devuelve el valor almacenado en la memoria acumuladora de la calculadora.
		Returns:
			Valor de la memoria
	 */
	@Override
	public double memoriaObtener() {
        double _implementacionMO__return = port.implementacionMO();
		return _implementacionMO__return;

	}
	/*
	 *	public double obtenerUltimoResultado()
		Devuelve el último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
		Returns:
			último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
	 */
	@Override
	public double obtenerUltimoResultado() {
        double _implementacionUR__return = port.implementacionUR();

		return _implementacionUR__return;
	}
	/*
	 *	public double multiplicar(double operando1, double operando2)
		Multiplica los dos operandos y devuelve el resultado.
		Parameters:
			operando1 - Operando 1
			operando2 - Operando 2
		Returns:
			Multiplicación de los dos operandos
	 */
	@Override
	public double multiplicar(double operando1, double operando2) {
        double _implementacionMultiplicar__return = port.implementacionMultiplicar(operando1, operando2);
		return _implementacionMultiplicar__return;
	}
	/*
	 *	public double restar(double operando1, double operando2)
		Resta el operando2 al operando1 y devuelve el resultado.
		Parameters:
			operando1 - Operando 1
			operando2 - Operando 2
		Returns:
			Resta del operando2 al operando1
	 */
	@Override
	public double restar(double operando1, double operando2) {
        double _implementacionRestar__return = port.implementacionRestar(operando1, operando2);
		return _implementacionRestar__return;
	}
	/*
	 *	public double sumar(double operando1, double operando2)
		Suma los dos operandos y devuelve el resultado.
		Parameters:
			operando1 - Operando 1
			operando2 - Operando 2
		Returns:
			Suma de los dos operandos
	 */
	@Override
	public double sumar(double operando1, double operando2) {
        double _implementacionSumar__return = port.implementacionSumar(operando1, operando2);
		return _implementacionSumar__return;
	}
	/*
	 *	public double dividir(double dividendo,	double divisor)throws Exception
		Divide el dividendo por el divisor y devuelve el resultado.
		Parameters:
			dividendo - Operando dividendo
			divisor - Operando divisor
		Returns:
			Division del dividendo por el divisor
		Throws:
			Exception - Excepción arrojada cuando se produce una división por cero o cuando se produce una indeterminación 0/0. La excepción debe contener un texto explicativo de la razón de la excepción.
	 */

	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
		// TODO Auto-generated method stub
		try {
            double _implementacionDividir__return = port.implementacionDividir(dividendo, divisor);
            System.out.println("implementacionDividir.result=" + _implementacionDividir__return);

        } catch (Exception_Exception e) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
		}
		return 0;
	}
	@Override
	public double operar(int numeroDeOperacion, double operando) throws Exception
	{
        try {
            double _operar__return = port.operar(numeroDeOperacion, operando);
            System.out.println("operar.result=" + _operar__return);

        } catch (Exception_Exception e) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
		return 0;
	}
	@Override
	public String[] getOperaciones(int numeroBotonesDisponibles)
	{
        java.util.List<java.lang.String> _getOperaciones__return = port.getOperaciones(numeroBotonesDisponibles);
        return (String[]) _getOperaciones__return.toArray();
	}


}
