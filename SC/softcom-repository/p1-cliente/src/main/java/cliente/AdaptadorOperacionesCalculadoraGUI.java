package cliente;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import CalculadoraGUI.ICalculadora;
import thriftStubs.ServicioCalculadora;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	// Escribir los métodos.
	//private OperacionesCalculadora operaciones = new OperacionesCalculadora();
	public static final String SERVIDOR = "localhost";
	public static final int PUERTO = 8585;
	
	private ServicioCalculadora.Client cliente;	
	
	public AdaptadorOperacionesCalculadoraGUI() {
		try {
			TTransport transport = new TSocket(SERVIDOR,PUERTO);
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			cliente = new ServicioCalculadora.Client(protocol);

		}catch(TException e) {
			System.err.println(e.getMessage());
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
			cliente.memoriaAniadir();
		} catch (TException e) {
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
			cliente.memoriaLimpiar();
		} catch (TException e) {
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
			return cliente.memoriaObtener();
		} catch (TException e) {
			// TODO Auto-generated catch block
			return Double.NaN;
		}
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
			return cliente.obtenerUltimoResultado();
		} catch (TException e) {
			// TODO Auto-generated catch block
			return Double.NaN;
		}
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
			return cliente.multiplicacion(operando1, operando2);
		} catch (TException e) {
			// TODO Auto-generated catch block
			return Double.NaN;
		}
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
			return cliente.restar(operando1, operando2);
		} catch (TException e) {
			// TODO Auto-generated catch block
			return Double.NaN;
		}
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
			return cliente.sumar(operando1, operando2);
		}catch (TException e) {
			return Double.NaN;
		}
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
		try {
			return cliente.division(dividendo, divisor);
		}catch (TException e) {
			return Double.NaN;
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
	public double elevarAlCuadrado(double operando){
		try {
			return cliente.elevarAlCuadrado(operando);
		} catch (TException e) {
			// TODO Auto-generated catch block
			return Double.NaN;
		}
	}
}
