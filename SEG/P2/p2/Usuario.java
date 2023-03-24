package p2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcRSAContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.operator.OperatorCreationException;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcContentSignerBuilder;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.bc.BcPKCS10CertificationRequestBuilder;


/**
* Esta clase implementa el comportamiento de un usuario en una Infraestructura de Certificación
* @author Seg Red Ser
* @version 1.0
*/
public class Usuario {
	
	private RSAKeyParameters keyPrivate = null;
	private RSAKeyParameters keyPublic = null;


	/**
	 * Método que genera las claves del usuario.
	 * @param fichClavePrivada: String con el nombre del fichero donde se guardará la clave privada en formato PEM
	 * @param fichClavePublica: String con el nombre del fichero donde se guardará la clave publica en formato PEM
     * @throws IOException 	
	
	 */
	public void generarClavesUsuario (String fichClavePrivada, String fichClavePublica) throws IOException{
		
		// Esto es nuevo respecto de la P1. Se debe instanciar un objeto de la clase GestionClaves proporcionada
		// Tanto en Usuario como en CA
		GestionClaves gc = new GestionClaves (); 
		
		// Asignar claves a los atributos correspondientes
		// Escribir las claves en un fichero en formato PEM 

		//IMPLEMENTAR POR EL ESTUDIANTE ESTUDIANTE
		AsymmetricCipherKeyPair pairKeys = gc.generarClaves(BigInteger.valueOf(17), 2048);

		keyPrivate = (RSAKeyParameters) pairKeys.getPrivate();
		keyPublic = (RSAKeyParameters) pairKeys.getPublic();
		
		PrivateKeyInfo privateKey = gc.getClavePrivadaPKCS8(this.keyPrivate);

		/*
		 * byte[] privada = gc.getClavePrivadaPKCS8(this.clavePrivada).getEncoded();
		 */
		
		byte[] bytePublic = gc.getClavePublicaSPKI(this.keyPublic).getEncoded();
		
		/*
		 * SubjectPublicKeyInfo publicKey = gc.getClavePublicaSPKI(this.clavePublica);
		 */
		
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PKCS8KEY_PEM_HEADER, privateKey.getEncoded(), fichClavePrivada);
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER, bytePublic, fichClavePublica);
	}



	
	/**
	 * Método que genera una petición de certificado en formato PEM, almacenando esta petición en un fichero.
	 * @param fichPeticion: String con el nombre del fichero donde se guardará la petición de certificado
	 * @throws IOException 
	 * @throws OperatorCreationException 
	 */
	public void crearPetCertificado(String fichPeticion, boolean loadKeys) throws OperatorCreationException, IOException {
		// IMPLEMENTAR POR EL ESTUDIANTE
 
	   	// Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS PRESENTACIÓN PRÁCTICA)
		// La solicitud se firma con la clave privada del usuario y se escribe en fichPeticion en formato PEM
		if(loadKeys)
			cargarClaves(); //Cargar claves del fichero NOMBRE_FICHERO_CLAVES
		else
			guardarClaves(); //Genera par de claves y las guardae en el fichero NOMBRE_FICHERO_CLAVES

		if(!clavesGeneradas())
			System.exit(0); //Si no se ha podido generar las claves cerramos el programa



		// IMPLEMENTAR POR EL ESTUDIANTE
		
	}
	
	
	/**
	 * Método que verifica un certificado de una entidad.
	 * @param fichCertificadoCA: String con el nombre del fichero donde se encuentra el certificado de la CA
	 * @param fichCertificadoUsu: String con el nombre del fichero donde se encuentra el certificado de la entidad
     	 * @throws CertException 
	 * @throws OperatorCreationException 
	 * @throws IOException 
	 * @throws FileNotFoundException 	
	 * @return boolean: true si verificación OK, false en caso contrario.
	 */
    public boolean verificarCertificadoExterno(String fichCertificadoCA, String fichCertificadoUsu)throws OperatorCreationException, CertException, FileNotFoundException, IOException {

    // IMPLEMENTAR POR EL ESTUDIANTE
	// Comprobar fecha validez del certificado
	// Si la fecha es válida, se comprueba la firma
	// Generar un contenedor para la verificación con la clave pública de CA,
	// el certificado del usuario tiene el resto de información
    	
   	// IMPLEMENTAR POR EL ESTUDIANTE
		return false;
	}	
}

	// EL ESTUDIANTE PODRÁ CODIFICAR TANTOS MÉTODOS PRIVADOS COMO CONSIDERE INTERESANTE PARA UNA MEJOR ORGANIZACIÓN DEL CÓDIGO