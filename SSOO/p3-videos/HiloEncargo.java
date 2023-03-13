package servidor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ssoo.videos.Dvd;
import ssoo.videos.MenuRaiz;
import ssoo.videos.Video;
import ssoo.videos.servidor.Cliente;
import ssoo.videos.servidor.Peticion;
/**
 * Clase destinada a implementar los hilos de encargo.
 * Actualmente está limitada a escribir por pantalla los datos de encargo que viene en la petici�n.
 * 
 * @author Álvaro & Bárbara
 * @version 1
 */
public class HiloEncargo implements Runnable{

	private Peticion peticion;
	private ColaTrabajos cola;
	private int numTrabajos;
	//AtomicInteger numTrabajos;
	private List<Trabajo> listJobs;
	private Lock lock;
	private Condition condition;
	/**
	 * Constructor de la clase HiloEncargo.
	 * @param peticion. Peticion. Petición del cliente que contiene los videos a transcodificar.
	 * @param cola. ColaTrabajos. Nexo entre HiloEncargo e HiloTranscodificado.
	 */
	public HiloEncargo (Peticion peticion, ColaTrabajos cola) {
		super();
		this.peticion = peticion;
		this.cola = cola;
		numTrabajos = 0;
		listJobs = new ArrayList<Trabajo>();
		lock = new ReentrantLock();
		condition = lock.newCondition();
		
	}
	@Override
	public void run() {
		Trabajo t;
		for(int i=0;peticion.getEncargo().getVideos().size() > i; i++) 
		{
			t = new Trabajo (peticion.getEncargo().getVideos().get(i));
			t = cola.encolar(t);
			if(t.getVideoTranscodificado()==null)
			{	
				t.putAviso(new Aviso (condition, lock));
				System.out.println("\nMeto un aviso");
			}
			listJobs.add(t);
			System.out.println("\nTrabajo: "+t);
		}
		System.out.println("\nNumero de trabajos:"+numTrabajos+ " del hilo:"+this.toString());
		lock.lock();
		for (Trabajo j:listJobs){
			if ( j.getVideoTranscodificado()==null) {
					numTrabajos++;
			}
		}
		System.out.println("\nEntramos en el bucle de trabajos\n");
		while (numTrabajos > 0) { 
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println("\nLlega el aviso! quedan "+numTrabajos+" trabajos\n");				
			numTrabajos--;
		}
		lock.unlock();
		List<Video> listaFinalVideos = new ArrayList<Video>();
		for (Trabajo w : listJobs) {
			listaFinalVideos.add(w.getVideoTranscodificado());
			System.out.println("Video trancodificado ---- "+w.getVideoTranscodificado());	
		}
		Dvd dvd = new Dvd("titulo", new MenuRaiz(listaFinalVideos), listaFinalVideos);
		Cliente usuario = peticion.getCliente();
		usuario.enviar(dvd);
		System.out.println("\nFin hilo encargo.\n");	
	}
}
