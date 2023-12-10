package cliente;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import CalculadoraGUI.CalculadoraGUI;
import wsCalculadora.OperacionesCalculadora;
import wsCalculadora.OperacionesCalculadoraService;

/** Esta es la clase que contiene el método main() de la aplicación de la calculadora y de la aplicación cleinte de la calculadora.
 * 
 */
public class ClienteCalculadora {

	/** Este es el método inicial de la aplicación. En él basta con instanciar CalculadoraGUI
	 * pasándole una instancia nueva de una clase que implementa OperacionesCalculadoraGUI.
	 * @param args Argumentos del método main()
	 */
    private static final QName SERVICE_NAME = new QName("http://calculadora/", "OperacionesCalculadoraService");

	public static void main(String[] args) {
	
		URL wsdlURL = OperacionesCalculadoraService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        OperacionesCalculadoraService ss = new OperacionesCalculadoraService(wsdlURL, SERVICE_NAME);
        OperacionesCalculadora port = ss.getOperacionesCalculadoraPort();

        new CalculadoraGUI(new AdaptadorOperacionesCalculadoraGUI(ss, port));
	}
}
