package piat.opendatasearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Nerea Calderón Gonzalo 50356369P
 *
 */

/**
 * Clase estática, que debe implementar la interfaz ParserCatalogo 
 * Hereda de DefaultHandler por lo que se deben sobrescribir sus métodos para procesar documentos XML 
 *
 */
public class ManejadorXML extends DefaultHandler implements ParserCatalogo {
	private String sNombreCategoria;	// Nombre de la categoría
	private List<String> lConcepts; 	// Lista con los uris de los elementos <concept> que pertenecen a la categoría
	private Map <String, Map<String,String>> hDatasets;	// Mapa con información de los dataset que pertenecen a la categoría
	private String sCodigoConcepto;
	private StringBuilder contenidoElemento;
	private Map <String, String> hData;
	private String atr;

	/**  
	 * @param sCodigoConcepto código de la categoría a procesar
	 * @throws SAXException, ParserConfigurationException 
	 */
	public ManejadorXML (String sCodigoConcepto) throws SAXException, ParserConfigurationException {
		this.sCodigoConcepto = sCodigoConcepto;
		hData = new HashMap <String, String>();
		lConcepts= new ArrayList<String>();
		hDatasets = new HashMap <String,Map<String, String>>();
		contenidoElemento = new StringBuilder(0);
		atr = null;
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
		// TODO 
		System.out.println("Termina el documento");
		
				
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		// TODO 

		contenidoElemento.setLength(0);
		/*
		System.out.println("Uri:" + uri);
		System.out.println("Nombre local:" + localName);
		System.out.println( " Nombre completo:" + qName);
		if(attributes.getLocalName(0))
			System.out.println("    ATRIB. id: " + attributes.getLocalName(0) );
*/
		switch(localName) {
			case "concept":
				atr = attributes.getLocalName(0);
				/*
				if(code.isTrue)
					lConcepts.add(atr);
					*/
				break;
			case "code":
				//contenidoElemento.append(true);
				break;
			default:
				// Acción a realizar en caso de que nomLocal no coincida con ninguno de los casos anteriores
				break;
		}
				
				
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		// TODO 
		
		switch(localName) {
		case "code":
			if(contenidoElemento.equals(sCodigoConcepto))
				lConcepts.add(atr);
		case "dataset":
			
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
