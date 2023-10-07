namespace java ServicioCalculadora

exception CalculadoraExcepcion {
    1: string mensaje
}

service ServicioCalculadora {
    /**
     * Suma dos números enteros.
     *
     * @param numero1 Primer número a sumar.
     * @param numero2 Segundo número a sumar.
     * @return Resultado de la suma.
     * @throws CalculadoraExcepcion Si ocurre un error durante la suma.
     */
    double suma(1: double operando1, 2: double operando2) throws (1: CalculadoraExcepcion excepcion),

    /**
     * Resta dos números enteros.
     *
     * @param numero1 Número al que se le restará.
     * @param numero2 Número que se restará.
     * @return Resultado de la resta.
     * @throws CalculadoraExcepcion Si ocurre un error durante la resta.
     */
    double resta(1: double operando1, 2: double operando2) throws (1: CalculadoraExcepcion excepcion),

    /**
     * Multiplica dos números enteros.
     *
     * @param numero1 Primer número a multiplicar.
     * @param numero2 Segundo número a multiplicar.
     * @return Resultado de la multiplicación.
     * @throws CalculadoraExcepcion Si ocurre un error durante la multiplicación.
     */
    double multiplicacion(1: double operando1, 2: double operando2) throws (1: CalculadoraExcepcion excepcion),

    /**
     * Divide dos números enteros.
     *
     * @param dividendo Número que se va a dividir.
     * @param divisor Número por el cual se va a dividir.
     * @return Resultado de la división.
     * @throws CalculadoraExcepcion Si ocurre un error durante la división, como la división por cero.
     */
    double division(1: double dividendo, 2: double divisor) throws (1: CalculadoraExcepcion excepcion),
    /*
	 *	public double elevarAlCuadrado(double operando)
		Eleva al cuadrado el operando y devuelve el resultado.
		Parameters:
			operando - Operando
		Returns:
			Elevado al cuadrado
	 */
	double elevarAlCuadrado(1: double operando),
		/*
	 *	public void memoriaAniadir()
		Añade a la memoria acumuladora el último resultado obtenido al realizar una operación aritmética.
	*/
	void memoriaAniadir(),
	/*
	 *	public void memoriaLimpiar()
		Pone a cero la memoria acumuladora de la calculadora.
	 */
	void memoriaLimpiar(),
	/*
	 *	public double memoriaObtener()
		Devuelve el valor almacenado en la memoria acumuladora de la calculadora.
		Returns:
			Valor de la memoria
	 */
	double memoriaObtener(),
	/*
	 *	public double obtenerUltimoResultado()
		Devuelve el último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
		Returns:
			último resultado obtenido al realizar una operación aritmética. Si no se ha realizado ninguna operación aritmética antes, devuelve cero.
	 */
	double obtenerUltimoResultado(),
}
