package servidor;

import java.util.concurrent.LinkedBlockingQueue;
import ssoo.videos.Video;
/**
 * Clase Trabajo que encapsula a la clase Video.
 * 
 * @author Álvaro y Bárbara
 *
 */
public class Trabajo {
	private Video videoOriginal;
	private Video videoTranscodificado;	
	private LinkedBlockingQueue<Aviso> avisos;
	/**
	 * Constructor de la clase Trabajo
	 * @param videoOriginal - Tipo Video. Clase que se encapsula.
	 */
	public Trabajo (Video videoOriginal) {
		this.videoOriginal = videoOriginal;
		avisos = new LinkedBlockingQueue<Aviso>();
	}
	/**
	 * Devuelve el vídeo original encapsulado.
	 * @return Video original
	 */
	public Video getVideoOriginal() {
		return videoOriginal;
	}
	/**
	 * Devuelve el vídeo transcodificado.
	 * @return Video Transcodificado
	 */
	public Video getVideoTranscodificado() {
		return videoTranscodificado;
	}
	/**
	 * Añadir aviso de un hilo encargo a la lista de avisos.
	 * @param a. Aviso de un hiloEncargo.
	 */
	public void putAviso (Aviso a) {
		try {
			avisos.put(a);
		} catch (InterruptedException e) {
			System.out.println("Error: imposible encolar aviso "+a);
			e.printStackTrace();
		}
	}
	/**
	 * Devuelve la cola de avisos.
	 * @return avisos. Cola de avisos
	 */
	public LinkedBlockingQueue<Aviso> getListaAvisos (){
		return avisos;
	}
	/**
	 * Insertar videoTranscodificado. Y avisamos a todos los hilosEncargos tengan
	 * este trabajo.
	 * @param videoTranscodificado
	 */
	public void setVideoTranscodificado(Video videoTranscodificado) 
	{
		this.videoTranscodificado = videoTranscodificado;
		for (Aviso a : getListaAvisos()) {
			try {
				a = avisos.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			a.avisar();
			System.out.println("Aviso enviado ...");
		}
	} 
}
