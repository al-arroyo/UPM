package calculadora;

import CalculadoraGUI.ICalculadora;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	// Escribir los métodos.
	private	double	memoria = 0;
	private	double	resultado = 0;
	private OperacionesCalculadora operaciones = new OperacionesCalculadora();
	/*
	 *	public void memoriaAniadir()
		Añade a la memoria acumuladora el último resultado obtenido al realizar una operación aritmética.
	*/
	@Override
	public void memoriaAniadir() {
		// TODO Auto-generated method stub
		operaciones.implementacionMA();
	}
	/*
	 *	public void memoriaLimpiar()
		Pone a cero la memoria acumuladora de la calculadora.
	 */
	@Override
	public void memoriaLimpiar() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
	public double dividir(double dividendo, double divisor) throws Exception {
		// TODO Auto-generated method stub
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
	public double elevarAlCuadrado(double operando) {
		// TODO Auto-generated method stub
		double resultado = 0;
		try {
			resultado = operaciones.implementacionCuadrado(operando);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
