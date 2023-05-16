package piat.opendatasearch;

/*  Nombre del alumno: 
 * 	DNI o documento identificativo:  
 */


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.stream.JsonReader;


public class JSONParserJunio implements Runnable{
	private String fichero;
	private Map<String, Map<String,String>> mResults;
	private String nombreHilo;


	public JSONParserJunio (String fichero, Map<String, Map<String, String>> mResults) {
		this.fichero=fichero;
		this.mResults=mResults;

	}


	
	@Override
	public void run (){
		Thread.currentThread().setName("JSON " + fichero);
		nombreHilo="["+Thread.currentThread().getName()+"] ";
		// TODO: Procesar el fichero para extraer las propiedades pertinentes y guardarlas en mResults


	}

}