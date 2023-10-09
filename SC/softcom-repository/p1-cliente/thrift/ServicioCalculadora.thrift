namespace java thriftStubs

exception CalculadoraExcepcion {
    1: string mensaje
}

service ServicioCalculadora {
    double sumar(1: double operando1, 2: double operando2),
    double restar(1: double operando1, 2: double operando2),
    double multiplicacion(1: double operando1, 2: double operando2),
    double division(1: double dividendo, 2: double divisor) throws (1: CalculadoraExcepcion excepcion),
	double elevarAlCuadrado(1: double operando),
	void memoriaAniadir(),
	void memoriaLimpiar(),
	double memoriaObtener(),
	double obtenerUltimoResultado(),
}