package piat.opendatasearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Clase para evaluar las expresiones XPath
 * Contiene un método estático, llamado evaluar() que se encarga de realizar las consultas XPath al fichero XML que se le pasa como parámetro 
 */
public class EvaluadorExamen{

	/**
	 * Método que se encarga de evaluar las expresiones XPath sobre el fichero XML generado en la práctica 4
	 * @param	ficheroXML	Fichero XML a evaluar
	 * @return	Una lista con la propiedad resultante de evaluar cada expresión XPath
	 * @throws	IOException
	 * @throws	XPathExpressionException 
	 * @throws	ParserConfigurationException 
	 * @throws	SAXException 
	 */
	public static List<Propiedad> evaluar (String ficheroXML) throws IOException, XPathExpressionException {
		// TODO: 
		// Realiza las 4 consultas XPath al documento XML de entrada que se indican en el enunciado en el apartado "3.2 Búsqueda de información y generación del documento de resultados."
		List<Propiedad> lPropiedad = new ArrayList<Propiedad>();
		try {		
			/*Abrir fichero xml*/	
			InputSource inputSource = new InputSource(ficheroXML);					// Creación de la fuente de datos
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	// Creación de la factoria
			//factory.setNamespaceAware(true);										// Configuración de la factoria para que trabaje con espacios de nombres
			DocumentBuilder builder = factory.newDocumentBuilder();						 	
			Document inputDoc = builder.parse (inputSource);	
			//se crea el objeto XPATH que evaluará las expresiones
			XPath xPath = XPathFactory.newInstance().newXPath();
			// Cada consulta devolverá una información que se añadirá a la colección List <Propiedad>, que es la que devuelve este método
			/*Rutas consultas*/
			//a) Contenido textual del elemento <theme>.
			final String xPathTheme="";
			//b) Número de elementos <theme> hijos de <dataset>.
			final String xPathCountTheme="";
			//c) Por cada elemento <dataset>, hijo de <datasets>, número de elementos <resource> cuyo atributo id es igual al atributo id del elemento <dataset>
			final String xPathDatasetID=""; //me coge todos los id de dataset que aparecen también en algún resoruce		
			//d) Contenido de cada uno de los elementos <title>, descendientes de <resource>. Si su contenido está vacío no se tendrá en cuenta. Si una ubicación está repetida sólo se añadirá una vez al documento de salida.
			final String xPathTitle="";

		
		}catch (Throwable t) {  t.printStackTrace();  }
		return lPropiedad;		
	}//del (main)

	/**
	 * Esta clase interna define una propiedad equivalente a "nombre":"valor" en JSON
	 */
	public static class Propiedad {
		public final String nombre;
		public final String valor;

		public Propiedad (String nombre, String valor){
			this.nombre=nombre;
			this.valor=valor;		
		}

		@Override
		public String toString() {
			return this.nombre+": "+this.valor;

		}

	} //Fin de la clase interna Propiedad

} //Fin de la clase XPATH_Evaluador
