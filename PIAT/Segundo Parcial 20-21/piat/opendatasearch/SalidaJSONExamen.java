package piat.opendatasearch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonWriter;
import piat.opendatasearch.XPATH_Evaluador.Propiedad;

/**
 * Clase usada para crear un fichero JSON
 * Contiene un método estático, llamado generar() que se encarga de generar un String en formato JSON a partir de la lista de propiedades que se le pasa como parámetro 
 */
public class SalidaJSONExamen {

	/**
	 * Método que se encarga de generar un String en formato JSON a partir de la lista de propiedades que se le pasa como parámetro
	 * @param	ficheroJSONSalida	Nombre del fichero JSON de salida
	 * @param	listaPropiedades	Lista de propiedades
	 */
	public static void generar(String ficheroJSONSalida,List<Propiedad> listaPropiedades) {
		// TODO:
		// - Crear objeto de la clase writer usando como fichero el parámetro ficheroJSONSalida
		// - Crear los arrays y objetos que debe tener el fichero JSON de salida
	}	
// Método auxiliar para buscar en el ArrayList el primer elemento que contenga el String pasado como parámetro en su nombre. Devuelve -1 si no lo encuentra
private static int buscarPropiedad(String nombre, List<Propiedad> listaPropiedades){
	for (int i=0;i<listaPropiedades.size();i++)
		if(listaPropiedades.get(i).nombre.equals(nombre)) return i;
	return -1;
}
}

