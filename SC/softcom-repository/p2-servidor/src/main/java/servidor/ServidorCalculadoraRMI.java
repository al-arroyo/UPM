package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import calculadoraRMI.ICalculadoraRMI;
/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ServidorCalculadoraRMI {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
	private final static int PUERTO = 8585;
	
	public static void main(String[] args) {

		// Instanciar los objetos necesarios.
		try {
			ICalculadoraRMI operaciones = new AdaptadorOperacionesCalculadoraRMI();
			ICalculadoraRMI remoteStub = (ICalculadoraRMI) UnicastRemoteObject.exportObject(operaciones, 0);
//			Registry registry = LocateRegistry.getRegistry();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Calculadora", remoteStub);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
