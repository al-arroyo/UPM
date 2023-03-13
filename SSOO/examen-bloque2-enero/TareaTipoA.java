import java.util.Random;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TareaTipoA extends Tarea
{
	private static final AtomicInteger idGen = new AtomicInteger ( 0 );
	private static final Random rand = new Random();
	private final int dificultad;
	private final int id;

	public TareaTipoA ( int dificultad, List<Tarea> prerrequisitos )
	{
		super ( prerrequisitos );
		this.dificultad = dificultad;
		this.id = idGen.incrementAndGet();
	}

	@Override
	public void ejecutar ()
	throws InterruptedException
	{
		depurar ( "Empieza la ejecución de la " + toString() );
		final int retardo = rand.nextInt ( dificultad ) + 1;
		Thread.sleep ( retardo * 100 + retardo);
		depurar ( "Termina la ejecución de la " + toString() );
	}

	@Override
	public String toString ()
	{
		return ( "Tarea-" + id );
	}

	private void depurar ( String mensaje )
	{
		final String yo = "[" + Thread.currentThread().getName() + "] ";
		System.out.println ( yo + mensaje );
	}
}
