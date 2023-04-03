package p2;
public class PrincipalExamen1 {
	
	public static void main(String[] args) throws Exception {
        		
		  // Instanciamos usuario y CA
		  Usuario u =new Usuario();
		  CA ca=new CA();
		  System.out.println("\nPrueba FUNCIONAMIENTO Pr�ctica 2, curso 2022.");			
		  System.out.println("\nPrueba 1 FUNCIONAMIENTO CORRECTO \n1. Trabajar como USUARIO.");			
		  System.out.println("\tOPCI�N: GENERAR PAREJA DE CLAVES");
		  u.generarClavesUsuario("pri.txt", "pu.txt");
		  System.out.println("\tOPCI�N: Crear petici�n de certificaci�n.");			   
		  u.crearPetCertificado("pet.txt");
		  System.out.println("2. Trabajar como AUTORIDAD DE CERTIFICACI�N.");
		  System.out.println("\tOPCI�N: Generar pareja de claves y certificado autofirmado.");
		  ca.generarClavesyCertificado();							
		  System.out.println("\tClaves y certificado X509 GENERADOS");		  
		  System.out.println("\tOPCI�N: Generar un certificado a partir de una petici�n.");
		  if (ca.certificarPeticion("pet.txt","certUsu.crt"))
		    	System.out.println("\tCertificado de usuario creado correctamente");
		  else
		    	System.out.println("\tNo se ha podido generar el certificado del usuario");
		  System.out.println("3. Trabajar como USUARIO.");
		  System.out.println("\tOPCI�N:  Verificar certificado externo.");
		  if (u.verificarCertificadoExterno(CA.NOMBRE_FICHERO_CRT, "certUsu.crt"))
		      System.out.println("\tEl certificado es v�lido");
	       else
	    	   System.out.println("\tEl certificado no se ha podido verificar.");
		  System.out.println("\nPrueba 2 FUNCIONAMIENTO INCORRECTO \n4. Trabajar como CA.");
		  System.out.println("\tOPCI�N: Generar pareja de claves y certificado autofirmado.");
		  ca.generarClavesyCertificado();							
		  System.out.println("\tClaves y certificado X509 GENERADOS");
		  System.out.println("5. Trabajar como USUARIO.");
		  System.out.println("\tOPCI�N:  Verificar certificado externo.");
		  if (u.verificarCertificadoExterno(CA.NOMBRE_FICHERO_CRT, "certUsu.crt"))
		      System.out.println("\tEl certificado es v�lido\n");
	       else
	    	   System.out.println("\tEl certificado no se ha podido verificar.\n");
	}	
}