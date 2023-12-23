//SOF_COM 23/24 DMC DTE.
//Este es un ejemplo desarrollado en clase que debe ser considerado como tal.
//Puede tener errores y/o estar incompleto.

package entidades;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InformacionReserva {

	private String nombre;
	private String habitacion;
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}
}
