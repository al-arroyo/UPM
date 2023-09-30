package calculadora;

import CalculadoraGUI.ICalculadora;

/** Esta clase sirve para adaptar la interfaz de la clase calculadora.OperacionesCalculadora a la interfaz
 * de CalculadoraGUI.ICalculadora.
 * Se peude utilizar un adaptador de clase o de objeto.
 */
public class AdaptadorOperacionesCalculadoraGUI implements ICalculadora {

	// Escribir los métodos.
	private	double	memoria = 0;
	private	double	resultado = 0;
	
	@Override
	public void memoriaAniadir() {
		// TODO Auto-generated method stub
		this.memoria = obtenerUltimoResultado();
	}
	@Override
	public void memoriaLimpiar() {
		// TODO Auto-generated method stub
		memoria = 0;
	}
	@Override
	public double memoriaObtener() {
		// TODO Auto-generated method stub
		return memoria;
	}
	@Override
	public double obtenerUltimoResultado() {
		// TODO Auto-generated method stub
		return resultado;
	}
	@Override
	public double multiplicar(double operando1, double operando2) {
		// TODO Auto-generated method stub
		resultado = operando1*operando2;
		return resultado;
	}
	@Override
	public double restar(double operando1, double operando2) {
		// TODO Auto-generated method stub
		resultado = (operando1-operando2);
		return resultado;
	}
	@Override
	public double sumar(double operando1, double operando2) {
		// TODO Auto-generated method stub
		resultado = (operando1+operando2);
		return resultado;
	}
	@Override
	public double dividir(double dividendo, double divisor) throws Exception {
		// TODO Auto-generated method stub
		try {
			resultado = (dividendo/divisor);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("El divisor no puede ser 0 o una indeterminacion 0/0");
		}
		return resultado;
	}
	@Override
	public double elevarAlCuadrado(double operando) {
		// TODO Auto-generated method stub
		resultado = (operando*operando);
		return resultado;
	}
}
