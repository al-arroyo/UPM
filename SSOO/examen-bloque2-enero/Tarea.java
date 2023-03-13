import java.util.List;

/**
 * Implementa una Tarea. Es una clase abstracta que será completada por
 * las clases que deriven de ella. Puede ser modificada, pero sin eliminar
 * nada de lo que hay. Se pueden añadir los atributos y/o métodos que se
 * considere necesario.
 ***********************************************************************
 *                                                                     *
 * @author alvaromiguel.arroyo.gonzalez@alumnos.upm.es *
 *                                                                     *
 ***********************************************************************
 */
public abstract class Tarea
{
	private final List<Tarea> prerrequisitos;
	private boolean ejecutando;


	/**
	 * Crea una tarea con los prerrequisitos indicados. Este constructor
	 * puede ser completado por el estudiante, pero no debe modificarse
	 * su interfaz, ni eliminarse nada de su contenido.
	 * @param prerrequisitos La lista de tareas que deben haber terminado
	 * antes de que pueda ejecutarse el método ejecutar() de esta tarea.
	 */
	protected Tarea ( List<Tarea> prerrequisitos )
	{
		this.prerrequisitos = prerrequisitos;
		ejecutando = false;
	}

	/**
	 * Ejecuta la tarea. Es implementado en las subclases. No debe
	 * modificarse.
	 * @throws InterruptedException si el hilo es interrumpido durante
	 * la ejecución de la tarea.
	 */
	public abstract void ejecutar ()
	throws InterruptedException;
	
	public boolean isEjecutando() {
		return ejecutando;
	}

	public void setEjecutando(boolean ejecutando) {
		this.ejecutando = ejecutando;
	}
	
}
