package servidor;

import ServicioCalculadora.ServicioCalculadora;
import servidor.AdaptadorOperacionesCalculadoraGUI;
import ServicioCalculadora.CalculadoraExcepcion;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ServidorCalculadoraGUI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	public static void main(String[] args) {

		// Instanciar los objetos necesarios.
		try {
			CalculadoraGUI calculadora = new CalculadoraGUI(new AdaptadorOperacionesCalculadoraGUI(null));

			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new TServer.Args(serverTransport).protocolFactory(new Factory()));

			System.out.println("Iniciando el servidor...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}