package piat.opendatasearch;

/* Clase estática para crear un String con el xml a partir de la información que se pasa como parámetro en mGraph*/


import java.util.Map;

public class SalidaXMLExamen {

	private static final String pGraph				= "\n\t\t<pc:graph id=\"#valor#\">";
	private static final String pTipoPostal			= "\n\t\t\t<pc:tipoPostalCode>#valor#</pc:tipoPostalCode>";
	private static final String pPostalCode			= "\n\t\t\t<pc:postalCode>#valor#</pc:postalCode>";


	public static String generar (Map<String, Map<String, String>> mGraph ){
		StringBuilder salidaXML= new StringBuilder();
		//TODO: Almacenar en salidaXML el texto XML válido respecto al esquema del documento graphs.xsd a partir de la información que hay en el mapa mGraph
		salidaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		salidaXML.append("<pc:graphs \n\txmlns:pc=\"http://www.piat.dte.upm.es/examenJunio\"");
		salidaXML.append("\n\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		salidaXML.append("\n\txsi:schemaLocation=\"http://www.piat.dte.upm.es/examenJunio graphs.xsd\">");
		graphsToXML(salidaXML, mGraph);
		salidaXML.append("\n</pc:graphs>");

		return salidaXML.toString();

	}
	
	public static void graphsToXML (StringBuilder salidaXML, Map<String, Map<String, String>> mGraph ){
		if(mGraph.size()>0){
			for (Map.Entry<String, Map<String, String>> entry : mGraph.entrySet()) {
				Map<String, String> mapaValor = entry.getValue();
				if (mapaValor.containsKey("title") && mapaValor.containsKey("description")) {
					salidaXML.append(pGraph.replace("#valor#", entry.getKey()));
					salidaXML.append(pTipoPostal.replace("#valor#", mapaValor.get("title")));
					salidaXML.append(pPostalCode.replace("#valor#", mapaValor.get("description")));
					salidaXML.append("\n\t\t</pc:graph>");
				}
			}
		}
	}
}
