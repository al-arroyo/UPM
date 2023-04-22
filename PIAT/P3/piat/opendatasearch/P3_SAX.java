package piat.opendatasearch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author Alvaro Miguel Arroyo Gonzalez 51549946T
 *
 */

/**
 * Clase principal de la aplicación de extracción de información del 
 * Portal de Datos Abiertos del Ayuntamiento de Madrid
 *
 */
public class P3_SAX {


	public static void main(String[] args) {
		
		// Verificar nº de argumentos correcto
		if (args.length!=4){
			String mensaje="ERROR: Argumentos incorrectos.";
			if (args.length>0)
				mensaje+=" He recibido estos argumentos: "+ Arrays.asList(args).toString()+"\n";
			mostrarUso(mensaje);
			System.exit(1);
		}		
		
		// TODO
		/* 
		* Volcar al fichero de salida los datos en el formato XML especificado por ResultadosBusquedaP3.xsd
		* Validar el fichero generado con el esquema recibido en el tercer argumento de main()
		*/

		//Validar los argumentos recibidos en main()
		validArgs(args);

		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			//Instanciar un objeto SAXParser e invocar a su método parse() pasando como parámetro un descriptor de fichero, cuyo nombre se recibió en el primer argumento de main(), y la instancia del objeto ManejadorXML 
			SAXParser saxParser = factory.newSAXParser();
			//Instanciar un objeto ManejadorXML pasando como parámetro el código de la categoría recibido en el primer argumento de main()
			ManejadorXML manejadorXML = new ManejadorXML(args[0]);
			saxParser.parse(new File(args[1]), manejadorXML);
			//Invocar al método getConcepts() del objeto ManejadorXML para obtener un List<String> con las uris de los elementos <concept> cuyo elemento <code> contiene el código de la categoría buscado
			//Invocar al método getDatasets() del objeto ManejadorXML para obtener un mapa con los datasets de la categoría buscada
			//Crear el fichero de salida con el nombre recibido en el cuarto argumento de main()
			String contenido = new GenerarXML().generar(manejadorXML.getConcepts(), manejadorXML.getDatasets());
			File salida = new File(args[3]);
			salida.delete();
			FileWriter writer = new FileWriter(salida, true);
			writer.write(contenido);
			writer.close();			
		} catch(SAXException | ParserConfigurationException | IOException e){
			e.printStackTrace();
		}

		System.exit(0);
	}
	
	
	/**
	 * Muestra mensaje de los argumentos esperados por la aplicación.
	 * Deberá invocase en la fase de validación ante la detección de algún fallo
	 *
	 * @param mensaje  Mensaje adicional informativo (null si no se desea)
	 */
	private static void mostrarUso(String mensaje){
		Class<? extends Object> thisClass = new Object(){}.getClass();
		
		if (mensaje != null)
			System.err.println(mensaje+"\n");
		System.err.println(
				"Uso: " + thisClass.getEnclosingClass().getCanonicalName() + " <códigoCategoría> <ficheroCatalogo> <ficheroXSDsalida> <ficheroXMLSalida>\n" +
				"donde:\n"+
				"\t códigoCategoría:\t código de la categoría de la que se desea obtener datos\n" +
				"\t ficheroCatalogo:\t path al fichero XML con el catálogo de datos\n" +
				"\t ficheroXSDsalida:\t nombre del fichero que contiene el esquema contra el que se tiene que validar el documento XML de salida\n"	+
				"\t ficheroXMLSalida:\t nombre del fichero XML de salida\n"
				);				
	}
	
	private static void validArgs(String[] args) {
		String regex[] = {"^[0-9]{3,4}(-[0-9A-Z]{3,8})*$",
					".*(catalogo.xml)$",
					".*(ResultadosBusquedaP3.xsd)$",
					"(.*xml)$"}; //PREGUNTAR VALE CONQUE SEA SOLO XML O TIENE QUE VERIFICAR ALGO MAS ???
		for (int i = 0; i < args.length; i++) {
			if (!args[i].matches(regex[i])) {
				// Código para manejar el caso en que args[i] no coincide con regex[j]
				throw new IllegalArgumentException("Argumento incorrecto: " + args[i]);
			}
		}		
	}	
}