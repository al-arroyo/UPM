package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import calculadoraRMI.ICalculadoraRMI;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ClienteCalculadoraRMI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	public static void main(String[] args) {
	
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			ICalculadoraRMI stubCliente = (ICalculadoraRMI) registry.lookup("Calculadora");

			// Ya se pueden invocar los métodos del objeto remoto a través de stubCliente
			System.out.println(stubCliente.sumar(2.3, 4.5));
		} catch (NotBoundException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
