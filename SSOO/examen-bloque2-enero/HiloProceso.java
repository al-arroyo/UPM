import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementa un hilo que ejecuta la tarea encomendada.
 ***********************************************************************
 *                                                                     *
 * @author alvaromiguel.arroyo.gonzalez@alumnos.upm.es *
 *                                                                     *
 ***********************************************************************
 */
public class HiloProceso implements Runnable
{
	private final Tarea tarea;
	private Lock lock;
	private Condition condition;

	/**
	 * Crea un hilo que se encargará de procesar la tarea.
	 * @param tarea la tarea a procesar.
	 */
	public HiloProceso ( Tarea tarea )
	{
		this.tarea = tarea;
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}

	/**
	 * Procesa la tarea encomendada. Primero espera a que se cumplan los
	 * prerrequisitos de la tarea, es decir, que hayan terminado las tareas
	 * de las que depende. A continuación, ejecuta la tarea y, por último,
	 * notifica la terminación a los hilos que estén esperando a que
	 * termine esta tarea.
	 */
	@Override
	public void run ()
	{
		lock.lock();
		try 
		{
			while(tarea.isEjecutando() == true)
			{				
				condition.signalAll();
				condition.await();
			}
			tarea.setEjecutando(true);
			tarea.ejecutar();
			tarea.setEjecutando(false);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally
		{			
			lock.unlock();
		}
	}
}
