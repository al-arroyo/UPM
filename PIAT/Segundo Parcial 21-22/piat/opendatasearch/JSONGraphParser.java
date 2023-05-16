package piat.opendatasearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.stream.JsonReader;


/* En esta clase se comportará como un hilo */

public class JSONGraphParser implements Runnable {
	private String fichero;
	private Map<String, Map<String,String>> mGraphValues;
	private String nombreHilo;
	
	public JSONGraphParser (String fichero, Map<String, Map<String, String>> mGraphValues) {
		this.fichero=fichero;
		this.mGraphValues=mGraphValues;
	}

	
	@Override
	public void run (){
		Thread.currentThread().setName("JSON " + fichero);
		nombreHilo="["+Thread.currentThread().getName()+"] ";

		// TODO: Procesar el fichero para extraer las propiedades pertinentes y guardarlas en mGraphValues
		Map<String, String> graphs=new HashMap<>();	// Aquí se almacenarán todos los graphs de un dataset cuyo objeto de nombre @type se corresponda con uno de los valores pasados en el la lista lConcepts
		boolean finProcesar=false;	// Para detener el parser si se han agregado a la lista graphs 5 graph
	
	    System.out.println(nombreHilo+"Empezar a descargar de internet el JSON");
	    try {
	    	InputStreamReader inputStream = new InputStreamReader(new URL(fichero).openStream(), "UTF-8"); 
			//TODO:
			//	- Crear objeto JsonReader a partir de inputStream
			//  - Consumir el primer "{" del fichero
			//  - Procesar los elementos del fichero JSON, hasta el final de fichero o hasta que finProcesar=true
			//		Si se encuentra el objeto @graph, invocar a procesar_graph()
			//		Descartar el resto de objetos
			//	- Si se ha llegado al fin del fichero, consumir el último "}" del fichero
			//  - Cerrar el objeto JsonReader
			JsonReader jsonReader = new JsonReader(inputStream);
			jsonReader.setLenient(true);  // Para poder saltarse valores con skipValue()

			// inicio del consumo de los evantos del fichero json

			jsonReader.beginObject(); // Consumo el "{" de apertura del objeto
			while (jsonReader.hasNext()){
				//jsonReader.beginObject(); // Consumo el "{" de apertura del objeto
					String name = jsonReader.nextName();
					/*switch(name){
						case "@context":
							procesar_context(jsonReader, graphs);
						break;
						case "@graph":
							finProcesar=procesar_graph(jsonReader, graphs);
							if (finProcesar) 
							break;
						default:
							jsonReader.skipValue();

					}*/
					if(name.equals("@context")){
						procesar_context(jsonReader, graphs);
					}
					else if (name.equals("@graph")){
						finProcesar=procesar_graph(jsonReader, graphs);
						if (finProcesar) break;
					} 
					else jsonReader.skipValue();
				}
				//jsonReader.endObject(); // Consumo el "}" de cierre del objeto
				jsonReader.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println(nombreHilo+"El fichero no existe. Ignorándolo");
		} catch (IOException e) {
			System.out.println(nombreHilo+"Hubo un problema al abrir el fichero. Ignorándolo" + e);
		}
	    mGraphValues.put(fichero, graphs); 	// Se añaden al Mapa de concepts de los Datasets

	}
	
	private boolean procesar_graph(JsonReader jsonReader, Map<String, String> graphs) throws IOException {
		boolean finProcesar=false;
		// TODO:
		//	- Consumir el primer "[" del array @graph
		//  - Procesar todos los objetos del array, hasta el final de fichero o hasta que finProcesar=true
		//  	- Consumir el primer "{" del objeto
		//  	- Procesar un objeto del array invocando al método procesar_un_graph()
		//  	- Consumir el último "}" del objeto
		// 		- Ver si se han añadido 5 graph a la lista, para en ese caso poner la variable finProcesar a true
		//	- Si se ha llegado al fin del array, consumir el último "]" del array
		jsonReader.beginArray();
		while (jsonReader.hasNext()){
						jsonReader.beginObject();
						procesar_un_graph(jsonReader, graphs);
						jsonReader.endObject();
						if (graphs.size() == 5){
							finProcesar = true;
							break;
						}
					}
					//jsonReader.endArray();
					return finProcesar;
	}

	/*	procesar_un_graph()
	 * 	Procesa un objeto del array @graph y lo añade a la lista graphs si en el objeto de nombre @type hay un valor que se corresponde con uno de la lista lConcepts
	 */
	
	private void procesar_un_graph(JsonReader jsonReader, Map<String, String> graphs) throws IOException {
		// TODO:
		//	- Procesar todas las propiedades de un objeto del array @graph, guardándolas en variables temporales
		//	- Una vez procesadas todas las propiedades, ver si la clave @type tiene un valor igual a alguno de los concept de la lista lConcepts. Si es así
		//	  guardar en un mapa Map<String,String> todos los valores de las variables temporales recogidas en el paso anterior y añadir este mapa al mapa graphs
		while (jsonReader.hasNext()){
			switch(jsonReader.nextName()){
				case "address":
					jsonReader.beginObject();
					while(jsonReader.hasNext()){
						switch(jsonReader.nextName()){
						case "postal-code":
							graphs.put("PostalCode", jsonReader.nextString());
							break;	
						default:
							jsonReader.skipValue();
						}
					}
					jsonReader.endObject();
					break;
				default:
					jsonReader.skipValue();
				}
			}
	}
	private void procesar_context(JsonReader jsonReader, Map<String, String> graphs) throws IOException {
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();
			if (name.equals("postal-code")){
				graphs.put("tipoPostalCode", jsonReader.nextString());
			}
			else jsonReader.skipValue();
		}
		jsonReader.endObject();
	}
}
