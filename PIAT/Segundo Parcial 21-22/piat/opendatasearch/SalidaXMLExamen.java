package piat.opendatasearch;

/* Clase estática para crear un String con el xml a partir de la información que se pasa como parámetro en mGraph*/


import java.util.Map;

public class SalidaXMLExamen {
	private static final String pResource		= "\n\t\t\t<pc:graph id=\"#valor#\">";
	private static final String pTitle			= "\n\t\t\t\t<pc:tipoPostalCode>#valor#</pc:tipoPostalCode>";
	private static final String pDescription	= "\n\t\t\t\t<pc:postalCode>#valor#</pc:postalCode>";



	public static String generar (Map<String, Map<String, String>> mGraph ){
		StringBuilder salidaXML= new StringBuilder();
		//TODO: Almacenar en salidaXML el texto XML válido respecto al esquema del documento graphs.xsd a partir de la información que hay en el mapa mGraph
		salidaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		salidaXML.append("<pc:graphs \txmlns:pc=\"http://www.piat.dte.upm.es/examenJunio\"");
		salidaXML.append("\n\t\t\t\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		salidaXML.append("\n\t\t\t\txsi:schemaLocation=\"http://www.piat.dte.upm.es/examenJunio graphs.xsd\">");


				for (Map.Entry<String, Map<String, String>> entry : mGraph.entrySet()) {
					Map<String, String> mapaValor = entry.getValue();
					if (mapaValor.containsKey("tipoPostalCode") && mapaValor.containsKey("PostalCode")) {
						salidaXML.append(pResource.replace("#valor#", entry.getKey()));
						salidaXML.append(pTitle.replace("#valor#", mapaValor.get("tipoPostalCode")));
						salidaXML.append(pDescription.replace("#valor#", mapaValor.get("PostalCode")));
						salidaXML.append("\n\t\t\t</pc:graph>");
					}
				}
		salidaXML.append("\n\t\t</pc:graphs>");
		
		
		return salidaXML.toString();

	}
	
}
