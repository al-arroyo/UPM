package calculadora;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://calculadora/", portName = "OperacionesCalculadoraPort", serviceName = "OperacionesCalculadoraService")
public class OperacionesCalculadora{
	private	double	memoria = 0;
	private	double	resultado = 0;

	@WebMethod(operationName = "implementacionSumar", action = "urn:ImplementacionSumar")
	public synchronized double implementacionSumar(double operando1, double operando2){
		resultado = (operando1+operando2);
		return resultado;
	}

	@WebMethod(operationName = "implementacionRestar", action = "urn:ImplementacionRestar")
	public synchronized double implementacionRestar(double operando1, double operando2){
				resultado = (operando1-operando2);
		return resultado;
	}

	@WebMethod(operationName = "implementacionMultiplicar", action = "urn:ImplementacionMultiplicar")
	public synchronized double implementacionMultiplicar(double operando1, double operando2){
		resultado = operando1*operando2;
		return resultado;
	}

	@WebMethod(operationName = "implementacionDividir", action = "urn:ImplementacionDividir")
	public synchronized double implementacionDividir(double operando1, double operando2) throws Exception{
		if (operando1 == 0 && operando2 == 0)
			throw new Exception("Error: es una indeterminacion 0/0");
		else if(operando1 != 1 && operando2 == 0)
			throw new Exception("Error: el divisor no puede ser 0");
		else
			resultado = (operando1/operando2);
		return resultado;
	}
	@WebMethod(operationName = "implementacionUR", action = "urn:ImplementacionUR")
	public synchronized double implementacionUR(){
		return resultado;
	}

	@WebMethod(operationName = "implementacionMA", action = "urn:ImplementacionMA")
	public synchronized void implementacionMA(){
		memoria = resultado;
	}
	
	@WebMethod(operationName = "implementacionML", action = "urn:ImplementacionML")
	public synchronized void implementacionML(){
		memoria = 0;
	}

	@WebMethod(operationName = "implementacionMO", action = "urn:ImplementacionMO")
	public synchronized double implementacionMO(){
		return memoria;
	}

	@WebMethod(operationName = "getOperaciones", action = "urn:GetOperaciones")
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

	@WebMethod(operationName = "operar", action = "urn:Operar")
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
		return resultado;
	}
}
