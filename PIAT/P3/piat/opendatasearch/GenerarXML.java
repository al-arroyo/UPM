package piat.opendatasearch;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Ponga aquí su nombre, apellidos y DNI
 *
 */


/**
 * Clase estática para crear un String que contenga el documento xml a partir de la información almacenadas en las colecciones 
 *
 */	
public class GenerarXML {
	

	/**  
	 * Método que deberá ser invocado desde el programa principal
	 * Le metemos la lista y mapa gordo para que nos lo ponga en formato XML , y lo convierte en un String, que habra que meter 
	 * en nuestro fichero de salida
	 * 
	 * @param Colecciones con la información obtenida del documento XML de entrada
	 * @return String con el documento XML de salida
	 */	
	public static String generar (List<String> lConcepts, Map<String, Map<String, String>> hDatasets){
		StringBuilder salidaXML= new StringBuilder();
		salidaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); 
		for (String aux : lConcepts) {
			salidaXML.append(aux + "\n");
		}
		//salidaXML.append(lConcepts.toString());
		// TODO
		//PREGUNTAR A JAVIER
		return salidaXML.toString();

	}

	
}
