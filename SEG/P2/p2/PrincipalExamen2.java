package p2;
public class PrincipalExamen2 {
	
	public static void main(String[] args) throws Exception {
		  // Instanciamos usuario y CA
		  Usuario u =new Usuario();
		  CA ca=new CA();
		  System.out.println("\nPrueba FUNCIONAMIENTO Practica 2, curso 2022.");	
		  System.out.println("\nFUNCIONAMIENTO INCORRECTO-CORRECTO \n1. Trabajar como USUARIO.");		
		  //System.out.println("\tOPCIoN: Crear peticion de certificacion.");		   
		  //u.crearPetCertificado("pet.txt");
		  System.out.println("\tOPCIoN: GENERAR PAREJA DE CLAVES");			
		  u.generarClavesUsuario("pri.txt", "pu.txt");
		  System.out.println("\tOPCIoN: Crear peticion de certificacion.");		   
		  u.crearPetCertificado("pet.txt");
		  System.out.println("2. Trabajar como AUTORIDAD DE CERTIFICACIoN.");
		  System.out.println("\tOPCIoN: Generar un certificado a partir de una peticion.");		   
		  if (ca.certificarPeticion("pet.txt","certUsu.cert"))
		    	System.out.println("\tCertificado de usuario creado correctamente");
		  else
		    	System.out.println("\tNo se ha podido generar el certificado del usuario");
		  System.out.println("\tOPCIoN: Generar pareja de claves y certificado autofirmado.");
		  ca.generarClavesyCertificado();							
		  System.out.println("\tClaves y certificado X509 GENERADOS");		  
		  System.out.println("\tOPCIoN: Generar un certificado a partir de una peticion.");		   
		  if (ca.certificarPeticion("pet.txt","certUsu.crt"))
		    	System.out.println("\tCertificado de usuario creado correctamente");
		  else
		    	System.out.println("\tNo se ha podido generar el certificado del usuario");
		  System.out.println("3. Trabajar como USUARIO.");
		  System.out.println("\tOPCIoN:  Verificar certificado externo.");	
		  if (u.verificarCertificadoExterno(CA.NOMBRE_FICHERO_CRT, "certUsu.crt"))
		      System.out.println("\tEl certificado es volido");
	       else
	    	   System.out.println("\tEl certificado no se ha podido verificar.");
		  System.out.println("4. Trabajar como CA.");
		  System.out.println("\tOPCIoN: Generar pareja de claves y certificado autofirmado.");
		  ca.generarClavesyCertificado();							
		  System.out.println("\tClaves y certificado X509 GENERADOS");
		  System.out.println("5. Trabajar como USUARIO.");
		  System.out.println("\tOPCIoN:  Verificar certificado externo.");
		  if (u.verificarCertificadoExterno(CA.NOMBRE_FICHERO_CRT, "certUsu.crt"))
		      System.out.println("\tEl certificado es volido");
	       else
	    	   System.out.println("\tEl certificado no se ha podido verificar.");
	}	
}