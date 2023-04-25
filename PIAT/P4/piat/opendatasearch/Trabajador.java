package piat.regExp;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Álvaro Miguel Arroyo Gonzalez 51549946T
 */
public class Trabajador implements Runnable {

	private final File fichero;
	private final Pattern pTraza;
	private final AtomicInteger lineasCorrectas,lineasIncorrectas;
	private final ConcurrentHashMap<String, String>  hmServidores;
	private final ConcurrentHashMap<String, AtomicInteger> hmEstadisticasAgregadas;	
	private final ConcurrentHashMap <String,Pattern> hmPatronesEstadisticasAgregadas;
	private final ConcurrentHashMap <String,AtomicInteger> hmUsuarios;	
	private final AtomicInteger numTrabajadoresTerminados;
	
	public Trabajador(String json) {

	}
		
	public void run (){
		Thread.currentThread().setName("Trabajador del fichero " + fichero.getName());
	    //System.out.println("["+Thread.currentThread().getName()+"] Empiezo");

		// Abrir el fichero y si no hay errores, procesar cada línea invocando al método procesarLinea()
	    try {
	    	BufferedReader brInput = new BufferedReader(new FileReader(fichero));
			try{
				String linea;
				while ( (linea = brInput.readLine())!= null){
					procesarLinea(linea);
				}
			}catch(Exception e){
				System.err.println("ERROR (leyendo el fichero): "+ e.getMessage());
			}finally{
				try {
					brInput.close();

				} catch (IOException e) {
					System.err.println("ERROR (cerrando el fichero)" + e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("ERROR (fichero no encontrado): "+ e.getMessage());
		}
		
		//System.out.println("["+Thread.currentThread().getName()+"] Termino");
		System.out.print (".");
		numTrabajadoresTerminados.incrementAndGet();
	}
		

	
}
