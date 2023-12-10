package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import CalculadoraGUI.ICalculadora;
import calculadoraRMI.CalculadoraExcepcion;
import calculadoraRMI.ICalculadoraRMI;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadoraRMI, ICalculadora{
	
	// Escribir los métodos.
	private static ICalculadoraRMI stubCliente;
	private final static int PUERTO = 8585;
	public AdaptadorOperacionesCalculadoraGUI() throws RemoteException
	{
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", PUERTO);
			stubCliente = (ICalculadoraRMI) registry.lookup("Calculadora");

		} catch (NotBoundException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 *	public void memoriaAniadir()
		Añade a la memoria acumuladora el último resultado obtenido al realizar una operación aritmética.
	*/
	@Override
	public void memoriaAniadir() {
		try {
			stubCliente.memoriaAniadir();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 *	public void memoriaLimpiar()
		Pone a cero la memoria acumuladora de la calculadora.
	 */
	@Override
	public void memoriaLimpiar() {
		try {
			stubCliente.memoriaLimpiar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 *	public double memoriaObtener()
		Devuelve el valor almacenado en la memoria acumuladora de la calculadora.
		Returns:
			Valor de la memoria
	 */
	@Override
	public double memoriaObtener() {
		try {
			return stubCliente.memoriaObtener();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 *	public double obtenerUltimoResultado()
		Devuelve el último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
		Returns:
			último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
	 */
	@Override
	public double obtenerUltimoResultado() {
		try {
			return stubCliente.obtenerUltimoResultado();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
		try {
			return stubCliente.multiplicar(operando1, operando2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
		try {
			return stubCliente.restar(operando1, operando2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
		try {
			return stubCliente.sumar(operando1, operando2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
			return stubCliente.dividir(dividendo, divisor);
		} catch (CalculadoraExcepcion e) {
			throw new Exception( e.getMessage() );
		} catch (RemoteException e) {
			return 0; // Esto es una alternativa...
		}
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
	public double elevarCuadrado(double operando) throws RemoteException {
		// TODO Auto-generated method stub
		return stubCliente.elevarCuadrado(operando);
	}

}
