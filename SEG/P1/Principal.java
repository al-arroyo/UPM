/**Fichero: Principal.java
 * Clase para comprobar el funcionamiento de las otras clases del paquete.
 * Asignatura: SEG
 * @author Profesores de la asignatura
 * @version 1.0
 */

import java.util.Scanner;

public class Principal {

	public static void main (String [ ] args) {
		int menu1;
		int menu2;
		Scanner sc = new Scanner(System.in);
		/* completar declaracion de variables e instanciación de objetos */
		Simetrica s = new Simetrica();
		Asimetrica a = new Asimetrica();
		String fileKey;
		String fileIn;
		String fileOut;
		String fileKeyPrivate;
		String fileKeyPublic;
		boolean p;
		do {
			System.out.println("¿Qué tipo de criptografía desea utilizar?");
			System.out.println("1. Simétrico.");
			System.out.println("2. Asimétrico.");
			System.out.println("3. Salir.");
			menu1 = sc.nextInt();
		
			switch(menu1){
				case 1:
					do{
						System.out.println("Elija una opción para CRIPTOGRAFIA SIMÉTRICA:");
						System.out.println("0. Volver al menú anterior.");
						System.out.println("1. Generar clave.");
						System.out.println("2. Cifrado.");
						System.out.println("3. Descifrado.");
						menu2 = sc.nextInt();

						switch(menu2){
							case 1:
								/*completar acciones*/
								System.out.println("Introduce nombre del fichero que contendra la clave");
								s.keyGenerator(sc.next());
								System.out.println("La clave se ha generado correctamente");
							break;
							case 2:
								/*completar acciones*/
								System.out.println("Introduce el nombre del fichero que contiene la clave: ");
								fileKey = sc.next();
								System.out.println("Introduce el nombre del fichero de entrada a cifrar: ");
								fileIn = sc.next();
								System.out.println("Introduce el nombre del fichero de salida con el mensaje cifrado: ");
								fileOut = sc.next();
								s.encode(fileIn, fileKey, fileOut);
							break;
							case 3:
								/*completar acciones*/
								System.out.println("Introduce el nombre del fichero que contiene la clave: ");
								fileKey = sc.next();
								System.out.println("Introduce el nombre del fichero de entrada a descifrar: ");
								fileIn = sc.next();
								System.out.println("Introduce el nombre del fichero de salida con el mensaje descifrado: ");
								fileOut = sc.next();
								s.encode(fileIn, fileKey, fileOut);
							break;
						}
					} while(menu2 != 0);
				break;
				case 2:
					do{
						System.out.println("Elija una opción para CRIPTOGRAFIA ASIMÉTRICA:");
						System.out.println("0. Volver al menú anterior.");
						System.out.println("1. Generar pareja de claves.");
						System.out.println("2. Cifrado.");
						System.out.println("3. Descifrado.");
						System.out.println("4. Firmar digitalmente.");
						System.out.println("5. Verificar firma digital.");
						menu2 = sc.nextInt();
				
						switch(menu2){
							case 1:
								/*completar acciones*/
								System.out.println("Introduce nombre del fichero que contendra la clave privada");
								fileKeyPrivate = sc.next();
								System.out.println("Introduce nombre del fichero que contendra la clave publica");
								fileKeyPublic = sc.next();
								a.keyGenerator(fileKeyPrivate, fileKeyPublic);
								System.out.println("El par de claves se ha generado correctamente");
							break;
							case 2:
								/*completar acciones*/
								System.out.println("Introduce Privada o Publica en funcion de la clave que vaya a usar para el cifrado");
								fileIn = sc.next();
								if(fileIn.equals("Privada")) {
									p=true;
									System.out.println("Introduce nombre del fichero de entrada que desea cifrar");
									fileIn = sc.next();
									System.out.println("Introduce nombre del fichero de la clave privada");
									fileKey = sc.next();
									System.out.println("Introduce nombre del fichero de salida cifrado");
									fileOut = sc.next();
								}
								else {
									p=false;
									System.out.println("Introduce nombre del fichero de entrada que desea cifrar");
									fileIn = sc.next();
									System.out.println("Introduce nombre del fichero de la clave publica");
									fileKey = sc.next();
									System.out.println("Introduce nombre del fichero de salida cifrado");
									fileOut = sc.next();
								}
								a.encode(p, fileIn, fileKey, fileOut);
							break;
							case 3:
								/*completar acciones*/
								System.out.println("Introduce Privada o Publica en funcion de la clave que vaya a usar para el descifrado");
								fileIn = sc.next();
								if(fileIn.equals("Privada")) {
									p=true;
									System.out.println("Introduce nombre del fichero de entrada que desea descifrar");
									fileIn = sc.next();
									System.out.println("Introduce nombre del fichero de la clave privada");
									fileKey = sc.next();
									System.out.println("Introduce nombre del fichero de salida descifrado");
									fileOut = sc.next();
								}
								else {
									p=false;
									System.out.println("Introduce nombre del fichero de entrada que desea descifrar");
									fileIn = sc.next();
									System.out.println("Introduce nombre del fichero de la clave publica");
									fileKey = sc.next();
									System.out.println("Introduce nombre del fichero de salida descifrado");
									fileOut = sc.next();
								}
								a.encode(p, fileIn, fileKey, fileOut);
							break;
							case 4:
								/*completar acciones*/
								System.out.println("Introduce nombre del fichero de entrada que tiene el mensaje en claro");
								fileIn = sc.next();
								System.out.println("Introduce nombre del fichero de la clave privada");
								fileKey = sc.next();
								System.out.println("Introduce nombre del fichero de salida para dejar la firma");
								fileOut = sc.next();
								a.signature(fileKey, fileIn, fileOut);
							break;
							case 5:
								/*completar acciones*/
								System.out.println("Introduce nombre del fichero de entrada que tiene el mensaje en claro");
								fileIn = sc.next();
								System.out.println("Introduce nombre del fichero de la clave publica");
								fileKey = sc.next();
								System.out.println("Introduce nombre del fichero firmado");
								fileOut = sc.next();
								if(a.checkSignature(fileKey, fileIn, fileOut) == true)
									System.out.println("La firma se ha verificado");
								else
									System.out.println("La firma no ha podido ser verificada");
							break;
						}
					} while(menu2 != 0);
				break;
			}			
		} while(menu1 != 3);
		sc.close();
	}
}