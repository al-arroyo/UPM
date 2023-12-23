package entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resultado {
	
	private double resultado;
	private String[] listaOperaciones;
	@XmlElement
	public double getResultado() {
		return resultado;
	}
	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
	@XmlElement
	public String[] getListaOperaciones() {
		return listaOperaciones;
	}
	public void setListaOperaciones(String[] listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}
}
