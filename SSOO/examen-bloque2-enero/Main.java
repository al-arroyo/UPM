import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Aplicación que crea tareas y las procesa.
 ***********************************************************************
 *                                                                     *
 * @author alvaromiguel.arroyo.gonzalez@alumnos.upm.es *
 *                                                                     *
 ***********************************************************************
 */
public class Main
{
	public static void main ( String args[] )
	throws InterruptedException
	{
		final BlockingQueue<Tarea> cola = new ArrayBlockingQueue<Tarea>(10);
		final int numTareas = (args.length>0)?Integer.parseInt(args[0]):uso();
		final GeneradorDeTareas generador = new GeneradorDeTareas ( numTareas, cola );
		final Thread hiloGenerador = new Thread ( generador );

		hiloGenerador.start();
		for ( int i = 0; i < numTareas; i++ )
		{
			final Tarea tarea = cola.take();
			final HiloProceso hilo = new HiloProceso(tarea);
			final Thread hiloProceso = new Thread (hilo);
			hiloProceso.start();
			//tarea.ejecutar();
		}
	}

	/**
	 * Lanza una excepción en el caso de que se ejecute la aplicación sin
	 * argumentos. No debe ser alterada.
	 * @throws IllegalArgumentException indicando cómo debe ejecutarse la
	 * aplicación.
	 */
	private static int uso ()
	throws IllegalArgumentException
	{
		final String m = "Se necesita un argumento numérico que indique el " +
				"número de tareas a generar";
		throw new IllegalArgumentException ( m );
	}
}
