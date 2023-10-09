package servidor;

import thriftStubs.ServicioCalculadora;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ServidorCalculadoraGUI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	private final static int PUERTO = 8585;
	
	public static void main(String[] args) {

		// Instanciar los objetos necesarios.
		try {
			TServerSocket serviceTransport = new TServerSocket(PUERTO);
			ServicioCalculadora.Iface handler = new AdaptadorOperacionesCalculadoraGUI();
			TProcessor processor = new ServicioCalculadora.Processor<>(handler);
			TServer server = new TSimpleServer(new TSimpleServer.Args(serviceTransport)
					.processor(processor));
			server.serve();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
