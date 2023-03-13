package servidor;

import java.io.IOException;
import ssoo.videos.PanelVisualizador;
import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;

public class HiloPeticiones {

	public static void main(String[] args) {
		ReceptorPeticiones recPeticiones = null;
		
		ColaTrabajos cola = new ColaTrabajos(10);
		int numHilosTranscodificacion = 5;
		PanelVisualizador.getPanel().registrarColaTrabajos(cola);

		try {
			System.out.println("Activación de la escucha.");
			recPeticiones = new ReceptorPeticiones();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Error al activar la escucha.");
		}
		Thread hilo_trans;
		for ( int i = 0; i < numHilosTranscodificacion -1 ; i++)
		{
			System.out.println("\n Crear hilo transcodificador: " + i);
			hilo_trans = new Thread (new HiloTranscodificador(cola));
			hilo_trans.start();
		}

		while (true) {
			try {	
				Peticion peticion = recPeticiones.recibirPeticion();
				
				System.out.println("Creación del hilo.");
				final Thread hilo = new Thread(new HiloEncargo(peticion, cola));
				hilo.start();
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error al recibir peticiones de encargo.");
			}
		}
	}
}
