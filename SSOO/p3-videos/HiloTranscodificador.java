package servidor;

import ssoo.videos.Transcodificador;
public class HiloTranscodificador implements Runnable {
	private static final String HILO_TRANSCODIFICADOR = "Hilo de transcodificación";
	private ColaTrabajos cola;
	private Trabajo t;	//obtenido de la cola de trabajos el original
	private Transcodificador transcodificador;


	public HiloTranscodificador (ColaTrabajos cola) {
		this.cola = cola;
		transcodificador = new Transcodificador();
	}
	@Override
	public void run()
	{
		Thread.currentThread().setName(HILO_TRANSCODIFICADOR);
		System.out.println("[" + Thread.currentThread().getName() + "]");
		while(true) 
		{
			t = cola.desencolar();
			System.out.println("Se ha desencolado el vídeo: " + t.getVideoOriginal().getNombre());
			t.setVideoTranscodificado(transcodificador.transcodificar(t.getVideoOriginal()));
		}
	}
}
