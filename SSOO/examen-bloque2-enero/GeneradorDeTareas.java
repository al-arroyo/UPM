import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GeneradorDeTareas implements Runnable
{
	private static final String error = "El número de tareas debe ser al menos 10";
	private static final Random rand = new Random();
	private int pendientes;
	private int creadas;
	private final BlockingQueue<Tarea> cola;
	private final Map<Integer,List<Tarea>> tareasNivel;

	public GeneradorDeTareas ( int numTareas, BlockingQueue<Tarea> cola )
	{
		if ( numTareas < 10 )
			throw new IllegalArgumentException ( error );
		pendientes = numTareas;
		creadas = 0;
		this.cola = cola;
		tareasNivel = new HashMap<Integer,List<Tarea>>();
	}

	@Override
	public void run ()
	{
		try
		{
			tareasNivel.put ( 0, new ArrayList<Tarea>() );
			for ( int nivel = 1; pendientes > 0; nivel++ )
			{
				tareasNivel.put ( nivel, new ArrayList<Tarea> () );
				crearTareas ( nivel );
			}
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}

	private int min ( int a, int b )
	{
		if ( a < b )
			return a;
		return b;
	}

	private void crearTareas ( int nivel )
	throws InterruptedException
	{
		final int totales = creadas + pendientes;
		final int est1 = rand.nextInt ( pendientes ) + 1;
		final int est2 = totales / (nivel+1) + 1;
		final int est3 = ( est1 + est2 ) / 2;
		final int aCrear = rand.nextInt ( min ( est3, pendientes ) ) + 1;

		for ( int i = 0; i < aCrear; i++ )
		{
			final Tarea tarea = nuevaTarea ( nivel );
			tareasNivel.get(nivel).add ( tarea );
			cola.put ( tarea );
		}
		pendientes = pendientes - aCrear;
		creadas = creadas + aCrear;
	}

	private Tarea nuevaTarea ( int nivel )
	{
		final List<Tarea> candidatas = tareasNivel.get(nivel-1);
		final List<Tarea> dependencias = dependenciasAleatorias ( candidatas );
		final int dificultad = rand.nextInt ( 49 ) + 1;
		final Tarea t = new TareaTipoA ( dificultad, dependencias );
		depurarTarea ( t, dependencias );
		return t;
	}

	private List<Tarea> dependenciasAleatorias ( List<Tarea> tareas )
	{
		final List<Tarea> resultado = new ArrayList<Tarea> ();
		int i;

		i = 0;
		while ( i < tareas.size() )
		{
			final boolean incluirla = rand.nextBoolean();
			if ( incluirla )
			{
				final Tarea t = tareas.get ( i );
				resultado.add ( t );
			}
			i++;
		}
		return resultado;
	}

	private void depurarTarea ( Tarea tarea, List<Tarea> dependencias )
	{
		final String cabecera = "La " + tarea.toString();
		final String depende = " depende de:";
		String cuerpo;

		cuerpo = "";
		for ( Tarea t : dependencias )
			cuerpo = cuerpo + " " + t.toString();
		if ( cuerpo.length() == 0 )
			System.out.println ( cabecera + " no tiene dependencias" );
		else
			System.out.println ( cabecera + depende + cuerpo );
	}
}
