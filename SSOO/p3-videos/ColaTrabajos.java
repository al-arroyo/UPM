package servidor;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import ssoo.videos.Cola;
import ssoo.videos.Video;
/**
 * Esta clase permite crea una cola de trabajos que
 * permite encolar y desencolar los mismos de forma
 * concurrente.
 * 
 * Implementa a la clase Cola.
 * 
 * @author Álvaro y Bárbara
 *
 */
public class ColaTrabajos implements Cola {

	private BlockingQueue <Trabajo> cola;
	private ConcurrentHashMap<Video,Trabajo> map;
	
	public ColaTrabajos (int maxJobs) {
		cola = new ArrayBlockingQueue<Trabajo>(maxJobs);
		map =  new ConcurrentHashMap<Video,Trabajo>();
	}
	
	/**
	 * Devuelve el tamaño de la cola.
	 */
	@Override
	public int numTrabajos() {
		return cola.size();
	}
	/**
	 * El método encolar mete un trabajo a la cola de trabajos si no esta encolado.
	 * Le debe decir cual es el trabajo que hay en la cola 
	 * @param trabajo - Elemento que se mete a la cola.
	 */
	public Trabajo encolar (Trabajo t) {
		Trabajo job = map.putIfAbsent(t.getVideoOriginal(), t);
		if (job == null) {
			try {
				cola.put(t);
			} catch (InterruptedException e) {
				System.out.println("Error: Imposible encolar el trabajo "+t);
			}
		}
		job = map.get(t.getVideoOriginal());
		return job;
	}
	/**
	 * Este método extrae de la cola de trabajos un elemento.
	 * @return Trabajo - Devuelve el trabajo que se extrae de la cola
	 */
	public Trabajo desencolar () {
		Trabajo t = null;
		try {
			t = cola.take();
		} catch (InterruptedException e) {
			System.out.println("Error: Imposible extraer trabajo: "+t);
			e.printStackTrace();
		}
		if (numTrabajos() == 0) {
			map.clear();
		}
		return t;
	}

}
