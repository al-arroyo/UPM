package piat.opendatasearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Alvaro Miguel Arroyo Gonzalez 51549946T
 *
 */

/**
 * Clase estática, que debe implementar la interfaz ParserCatalogo 
 * Hereda de DefaultHandler por lo que se deben sobrescribir sus métodos para procesar documentos XML 
 *
 */
public class ManejadorXML extends DefaultHandler implements ParserCatalogo {
	private List<String> lConcepts; 	// Lista con los uris de los elementos <concept> que pertenecen a la categoría
	private Map <String, Map<String,String>> hDatasets;	// Mapa con información de los dataset que pertenecen a la categoría
	private String sCodigoConcepto;
	private StringBuilder contenidoElemento;
	private Map <String, String> hData;
	private Map<String , String> hDataAux;
	private String idConcept;
	private String idData;
	private AtomicInteger nivel;
	private AtomicInteger nivelEncontrado;

	/**  
	 * @param sCodigoConcepto código de la categoría a procesar
	 * @throws SAXException, ParserConfigurationException 
	 */
	public ManejadorXML (String sCodigoConcepto) throws SAXException, ParserConfigurationException {
		this.sCodigoConcepto = sCodigoConcepto;
		lConcepts= new ArrayList<String>();
		hData = new HashMap <String, String>();
		hDataAux = new HashMap<String, String>();
		hDatasets = new HashMap <String,Map<String, String>>();
		contenidoElemento = new StringBuilder(0);
		idConcept = null;
		idData = null;
		nivel = new AtomicInteger(1);
		nivelEncontrado = new AtomicInteger(0);

	}

	 //===========================================================
	 // Métodos a implementar de la interfaz ParserCatalogo
	 //===========================================================

	/**
	 * <code><b>getConcepts</b></code>
	 *	Devuelve una lista con información de los <code><b>concepts</b></code> resultantes de la búsqueda. 
	 * <br> Cada uno de los elementos de la lista contiene la <code><em>URI</em></code> del <code>concept</code>
	 * 
	 * <br>Se considerarán pertinentes el <code><b>concept</b></code> cuyo código
	 *  sea igual al criterio de búsqueda y todos sus <code>concept</code> descendientes.
	 *  
	 * @return
	 * - List  con la <em>URI</em> de los concepts pertinentes.
	 * <br>
	 * - null  si no hay concepts pertinentes.
	 * 
	 */
	@Override	
	public List<String> getConcepts() {
		return lConcepts;
	}

	/**
	 * <code><b>getDatasets</b></code>
	 * 
	 * @return Mapa con información de los <code>dataset</code> resultantes de la búsqueda.
	 * <br> Si no se ha realizado ninguna  búsqueda o no hay dataset pertinentes devolverá el valor <code>null</code>
	 * <br> Estructura de cada elemento del map:
	 * 		<br> . <b>key</b>: valor del atributo ID del elemento <code>dataset</code>con la cadena de la <code><em>URI</em></code>  
	 * 		<br> . <b>value</b>: Mapa con la información a extraer del <code>dataset</code>. Cada <code>key</code> tomará los valores <em>title</em>, <em>description</em> o <em>theme</em>, y <code>value</code> sus correspondientes valores.

	 * @return
	 *  - Map con información de los <code>dataset</code> resultantes de la búsqueda.
	 *  <br>
	 *  - null si no hay datasets pertinentes.  
	 */	
	@Override
	public Map<String, Map<String, String>> getDatasets() {
		return hDatasets;
	}
	
	
	//===========================================================
	// Métodos a implementar de SAX DocumentHandler
	//===========================================================
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		// TODO 
		System.out.println("Empieza el documento");
		
	}
	
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		// TODO ;
		System.out.println("Termina el documento");
		
				
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		// TODO 

		contenidoElemento.setLength(0);
		switch(qName) {
			case "concepts":
				nivel.incrementAndGet();
				if(lConcepts.size() != 0 && nivelEncontrado.get()==0){
					nivelEncontrado.addAndGet(nivel.get());
				}
			break;
			case "concept":
				nivel.incrementAndGet();
				idConcept = attributes.getValue("id");
				//si idData no es nulo , recorremos la lista de conceptos y 
				//si un concepto concuerda con el idConcept entonces el hData es valido
				//y se añade al mapa gordo
				if(idData != null) {
					for (String aux : lConcepts)
						if(aux.equals(idConcept)) {
							if(hData.containsKey("title") && hData.containsKey("description") && hData.containsKey("theme")
							   && !hDatasets.containsKey(idData) && idConcept.contains(lConcepts.get(0))) {
								//Necesito un mapa auxiliar para que no me elimine los datos del mapa gordo
								hDataAux = new HashMap<>();
								hDataAux.putAll(hData);
								hDatasets.putIfAbsent(idData, hDataAux);
							}
							break;
						}
				}
				break;
			case "dataset":
				//contenidoElemento.append(true);
				idData = attributes.getValue("id");
				break;
			default:
				// Acción a realizar en caso de que nomLocal no coincida con ninguno de los casos anteriores
				break;
		}
				
	// PREGUNTAR COMO DEBUGGEAR SI SE VE EL MAPA O NO	
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		// TODO 
		
		switch(qName) {
		case "concepts":
			nivel.decrementAndGet();	
			break;
		case "concept":
			nivel.decrementAndGet();
			break;
		case "code":
		//Cuando el codigo que le pasamos de argumento al manejador coincide con el code leido del xml , añadimos el concepto id a la lista de concepts
		if(contenidoElemento.toString().equals(sCodigoConcepto))
			lConcepts.add(idConcept);
		//Si el nivel encontrado es menor o igual que el nivel actual y el codigo del concepto es valido , añadimos el concepto id a la lista de concepts
		if(nivelEncontrado.get() < nivel.get() &&
			nivelEncontrado.get() != 0 && idConcept.contains(lConcepts.get(0)))
			lConcepts.add(idConcept);
			break;
		case "title":
			if(hData.size() == 0 && !hData.containsKey(null) && !hData.containsValue(null))
				hData.put("title", contenidoElemento.toString());
			break;
		case "description":
			if(hData.size() == 1 && !hData.containsKey(null) && !hData.containsValue(null))
				hData.put("description", contenidoElemento.toString());
			//hData.put("description", contenidoElemento.toString());
			break;
		case "theme":
			if(hData.size() == 2 && !hData.containsKey(null) && !hData.containsValue(null))
				hData.put("theme", contenidoElemento.toString());
			break;
		case "dataset":
			hData.clear();
			break;
		}
		contenidoElemento.setLength(0);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		// TODO 
		contenidoElemento.append(ch,start,length);
				
	}
}