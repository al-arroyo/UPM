package servidor;

import java.rmi.RemoteException;

import calculadora.OperacionesCalculadora;
import calculadoraRMI.CalculadoraExcepcion;
import calculadoraRMI.ICalculadoraRMI;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraRMI implements ICalculadoraRMI {
	
	// Escribir los métodos.
	private OperacionesCalculadora operaciones = new OperacionesCalculadora();
	AdaptadorOperacionesCalculadoraRMI() throws RemoteException
	{
		operaciones = new OperacionesCalculadora();
	}

	/*
	 *	public void memoriaAniadir()
		Añade a la memoria acumuladora el último resultado obtenido al realizar una operación aritmética.
	*/
	@Override
	public void memoriaAniadir() {
		operaciones.implementacionMA();
	}
	/*
	 *	public void memoriaLimpiar()
		Pone a cero la memoria acumuladora de la calculadora.
	 */
	@Override
	public void memoriaLimpiar() {
		operaciones.implementacionML();
	}
	/*
	 *	public double memoriaObtener()
		Devuelve el valor almacenado en la memoria acumuladora de la calculadora.
		Returns:
			Valor de la memoria
	 */
	@Override
	public double memoriaObtener() {
		return operaciones.implementacionMO();
	}
	/*
	 *	public double obtenerUltimoResultado()
		Devuelve el último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
		Returns:
			último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
	 */
	@Override
	public double obtenerUltimoResultado() {
		return operaciones.implementacionUR();
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
		return operaciones.implementacionMultiplicar(operando1, operando2);
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
		return operaciones.implementacionRestar(operando1, operando2);
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
		return operaciones.implementacionSumar(operando1, operando2);
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
	public double dividir(double dividendo, double divisor) throws CalculadoraExcepcion {
		return operaciones.implementacionDividir(dividendo, divisor);
	}
	/*
	 *	public double elevarAlCuadrado(double operando)
		Eleva al cuadrado el operando y devuelve el resultado.
		Parameters:
			operando - Operando
		Returns:
			Elevado al cuadrado
	 */
	@Override
	public double elevarAlCuadrado(double operando){
		return	operaciones.implementacionCuadrado(operando);
	}
	@Override
	public double multiplicacion(double operando1, double operando2) throws TException {
		// TODO Auto-generated method stub
		return operaciones.implementacionMultiplicar(operando1, operando2);
	}
	@Override
	public double division(double dividendo, double divisor) throws CalculadoraExcepcion, TException {
		// TODO Auto-generated method stub
		try {
			return operaciones.implementacionDividir(dividendo, divisor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Double.NaN;
		}
	}
}
