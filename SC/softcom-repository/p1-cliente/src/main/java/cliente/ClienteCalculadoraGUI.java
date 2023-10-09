package cliente;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import CalculadoraGUI.CalculadoraGUI;
import CalculadoraGUI.ICalculadora;
import thriftStubs.CalculadoraExcepcion;
import thriftStubs.ServicioCalculadora;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ClienteCalculadoraGUI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	public static void main(String[] args) {
	
		ICalculadora adaptador = new AdaptadorOperacionesCalculadoraGUI();
		new CalculadoraGUI(adaptador);
	}
}
