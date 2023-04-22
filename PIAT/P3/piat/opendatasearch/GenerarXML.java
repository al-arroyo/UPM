package piat.opendatasearch;

import java.util.List;
import java.util.Map;

/**
 * @author Alvaro Miguel Arroyo Gonzalez 51549946T
 *
 */


/**
 * Clase estática para crear un String que contenga el documento xml a partir de la información almacenadas en las colecciones 
 *
 */	
public class GenerarXML {
	private static final String pConcept		= "\n\t\t\t<concept id=\"#ID#\"/>" ;
	private static final String pDataset		= "\n\t\t\t<dataset id=\"#ID#\">";
	private static final String pTitle			= "\n\t\t\t\t<title>#valor#</title>";
	private static final String pDescription	= "\n\t\t\t\t<description>#valor#</description>";
	private static final String pTheme			= "\n\t\t\t\t<theme>#valor#</theme>";


	/**  
	 * Método que deberá ser invocado desde el programa principal
	 * Le metemos la lista y mapa gordo para que nos lo ponga en formato XML , y lo convierte en un String, que habra que meter 
	 * en nuestro fichero de salida
	 * 
	 * @param Colecciones con la información obtenida del documento XML de entrada
	 * @return String con el documento XML de salida
	 */	
	public static String generar (List<String> lConcepts, Map<String, Map<String, String>> hDatasets, String [] args){
		StringBuilder salidaXML= new StringBuilder();
		salidaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); 
		// TODO
		//PREGUNTAR A JAVIER
		salidaXML.append("<searchResults \txmlns=\"http://www.piat.dte.upm.es/practica3\"");
		salidaXML.append("\n\t\t\t\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		salidaXML.append("\n\t\t\t\txsi:schemaLocation=\"http://www.piat.dte.upm.es/practica3 ResultadosBusquedaP3.xsd\">");
		salidaXML.append("\n\t<summary>");
		salidaXML.append("\n\t\t<query>"+args[1]+"</query>");
		salidaXML.append("\n\t\t<numConcepts>"+lConcepts.size()+"</numConcepts>");
		salidaXML.append("\n\t\t<numDatasets>"+hDatasets.size()+"</numDatasets>");
		salidaXML.append("\n\t</summary>");
		salidaXML.append("\n\t<results>");

		salidaXML.append("\n\t\t<concepts>");
		for (String aux : lConcepts) {
			salidaXML.append(pConcept.replace("#ID#", aux));
		}
		salidaXML.append("\n\t\t</concepts>");
		if(hDatasets.size() != 0){

			salidaXML.append("\n\t\t<datasets>");
			
			for (Map.Entry<String, Map<String, String>> entry : hDatasets.entrySet()) {
				Map<String, String> mapaValor = entry.getValue();
				if (mapaValor.containsKey("title") && mapaValor.containsKey("description") && mapaValor.containsKey("theme")) {
					salidaXML.append(pDataset.replace("#ID#", entry.getKey()));
					salidaXML.append(pTitle.replace("#valor#", mapaValor.get("title")));
					salidaXML.append(pDescription.replace("#valor#", mapaValor.get("description")));
					salidaXML.append(pTheme.replace("#valor#", mapaValor.get("theme")));
					salidaXML.append("\n\t\t\t</dataset>");
				}
			}
			salidaXML.append("\n\t\t</datasets>");
		}
		salidaXML.append("\n\t</results>");
		salidaXML.append("\n</searchResults>");
		return salidaXML.toString();
	}

	
}