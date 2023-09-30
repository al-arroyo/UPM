package calculadora;

import CalculadoraGUI.ICalculadora;

/** Esta clase contiene la implementación final de cada operación disponible en la calculadora.
 * Debe contener un método público de instancia por cada método de la interfaz CalculadoraGUI.ICalculadora.
 * Para evitar confusiones, se recomienda que cada uno de los métodos citados anteponga 'implementacion' al
 * nombre del método de CalculadoraGUI.ICalculadora. Por ejemplo: si se desea crear el método que implementa
 * la suma, su nombre en esta clase será 'implementacionSumar'.
 */
public class OperacionesCalculadora{
	private	double	memoria = 0;
	private	double	resultado = 0;
	// Escribir los métodos.
	AdaptadorOperacionesCalculadoraGUI adaptador = new AdaptadorOperacionesCalculadoraGUI();
	public double implementacionSumar(double operando1, double operando2){
		resultado = (operando1+operando2);
		return resultado;
	}
	public double implementacionRestar(double operando1, double operando2){
				resultado = (operando1-operando2);
		return resultado;
	}
	public double implementacionMultiplicar(double operando1, double operando2){
		resultado = operando1*operando2;
		return resultado;
	}
	public double implementacionDividir(double operando1, double operando2) throws Exception{
		try {
			resultado = (operando1/operando2);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("El divisor no puede ser 0 o una indeterminacion 0/0");
		}
		return resultado;
	}
	public double implementacionCuadrado(double operando) throws Exception{
		resultado = (operando*operando);
		return resultado;
	}
	public double implementacionUR(){
		return resultado;
	}
	public void implementacionMA(){
		this.memoria += resultado;
	}
	public void implementacionML(){
		memoria = 0;
	}
	public double implementacionMO(){
		return memoria;
	}
}
