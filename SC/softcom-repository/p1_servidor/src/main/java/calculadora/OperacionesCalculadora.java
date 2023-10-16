package calculadora;

import thriftStubs.CalculadoraExcepcion;

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
	//AdaptadorOperacionesCalculadoraGUI adaptador = new AdaptadorOperacionesCalculadoraGUI();
	public synchronized double implementacionSumar(double operando1, double operando2){
		resultado = (operando1+operando2);
		return resultado;
	}
	public synchronized double implementacionRestar(double operando1, double operando2){
				resultado = (operando1-operando2);
		return resultado;
	}
	public synchronized double implementacionMultiplicar(double operando1, double operando2){
		resultado = operando1*operando2;
		return resultado;
	}
	public synchronized double implementacionDividir(double operando1, double operando2) throws CalculadoraExcepcion{
		if (operando1 == 0 && operando2 == 0)
			throw new CalculadoraExcepcion("Error: es una indeterminacion 0/0");
		else if(operando1 != 1 && operando2 == 0)
			throw new CalculadoraExcepcion("Error: el divisor no puede ser 0");
		else
			resultado = (operando1/operando2);
		return resultado;
	}
	public synchronized double implementacionCuadrado(double operando){
		resultado = (operando*operando);
		return resultado;
	}
	public synchronized double implementacionUR(){
		return resultado;
	}
	public synchronized void implementacionMA(){
		memoria = resultado;
	}
	public synchronized void implementacionML(){
		memoria = 0;
	}
	public synchronized double implementacionMO(){
		return memoria;
	}
}
