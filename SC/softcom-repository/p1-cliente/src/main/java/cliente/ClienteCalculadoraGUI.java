package cliente;

import ServicioCalculadora.ServicioCalculadora;
import ServicioCalculadora.CalculadoraExcepcion;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ClienteCalculadoraGUI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	public static void main(String[] args) {

		// Instanciar los objetos necesarios.
		try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();

            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            ServicioCalculadora.Client client = new ServicioCalculadora.Client(protocol);

            AdaptadorOperacionesCalculadoraGUI adaptador = new AdaptadorOperacionesCalculadoraGUI(client);

            // Realiza operaciones de calculo utilizando adaptador
            adaptador.sumar(1, 2);
            adaptador.dividir(10, 5);

            transport.close();
        } catch (CalculadoraExcepcion e) {
            System.err.println("Error en la operacion de la calculadora: " + e.getMensaje());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
