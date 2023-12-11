package calculadora;

import javax.jws.WebMethod;
import javax.jws.WebService;

public class OperacionesCalculadora{
	private	double	memoria = 0;
	private	double	resultado = 0;

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

	public synchronized double implementacionDividir(double operando1, double operando2) throws Exception{
		if (operando1 == 0 && operando2 == 0)
			throw new Exception("Error: es una indeterminacion 0/0");
		else if(operando1 != 1 && operando2 == 0)
			throw new Exception("Error: el divisor no puede ser 0");
		else
			resultado = (operando1/operando2);
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

	public synchronized String[] getOperaciones(int numeroBotonesDisponibles)
	{
		String [] operaciones = {"x^2","sqtr(x)","ln(x)","tg(x)"};
		int i = 0;
		String []aux = new String[numeroBotonesDisponibles] ;
		if(numeroBotonesDisponibles < 4)
		{
			aux[i] = operaciones[i];
			i++;
			if(i == numeroBotonesDisponibles)
				return aux;
		}
		return operaciones;
	}

	public synchronized double operar(int numeroDeOperacion, double operando) throws Exception{
		double resultado = 0;
		switch(numeroDeOperacion)
		{
			case 0:
				resultado = operando * operando;
				break;
			case 1:
				resultado = Math.sqrt(operando);
				break;
			case 2:
				resultado = Math.log(operando);
				break;
			case 3:
				resultado = Math.tan(operando);
				break;
			default:
				throw new Exception ("Operacion desconocida");

		}
		memoria = resultado;
		return memoria;
	}
}
