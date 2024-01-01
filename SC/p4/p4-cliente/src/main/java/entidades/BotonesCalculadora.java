package entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BotonesCalculadora {
	private String[] listaOperaciones;

	@XmlElement
	public String[] getListaOperaciones() {
		return listaOperaciones;
	}

	public void setListaOperaciones(String[] listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

}
