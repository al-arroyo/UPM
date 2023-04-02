package p2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Date;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.operator.ContentSigner;
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
		AsymmetricCipherKeyPair keyPair = gc.generarClaves(BigInteger.valueOf(1), 2048);
		keyPrivate = (RSAKeyParameters) keyPair.getPrivate();
		keyPublic = (RSAKeyParameters) keyPair.getPublic();
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PKCS10_PEM_HEADER, gc.getClavePrivadaPKCS8(keyPrivate).getEncoded(), fichClavePrivada);
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER, gc.getClavePrivadaPKCS8(keyPublic).getEncoded(), fichClavePublica);		
	}



	
	/**
	 * Método que genera una petición de certificado en formato PEM, almacenando esta petición en un fichero.
	 * @param fichPeticion: String con el nombre del fichero donde se guardará la petición de certificado
	 * @throws IOException 
	 * @throws OperatorCreationException 
	 */
	public void crearPetCertificado(String fichPeticion) throws OperatorCreationException, IOException {
		// IMPLEMENTAR POR EL ESTUDIANTE
		
	   	// Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS PRESENTACIÓN PRÁCTICA)
		// La solicitud se firma con la clave privada del usuario y se escribe en fichPeticion en formato PEM

		// IMPLEMENTAR POR EL ESTUDIANTE

		X500Name subject = new X500Name("CN=Álvaro Miguel Arroyo Gonzalez, OU=Seguridad, O=ETSII, C=ES");
		PKCS10CertificationRequestBuilder p10Builder = new BcPKCS10CertificationRequestBuilder(subject, keyPublic);
		BcContentSignerBuilder csBuilder = buildContentSignerBuilder();
		PKCS10CertificationRequest p10Request = p10Builder.build(csBuilder.build(keyPrivate));
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PKCS10_PEM_HEADER, p10Request.getEncoded(), fichPeticion);
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
		GestionClaves gc = new GestionClaves();
		X509CertificateHolder certCA = (X509CertificateHolder) GestionObjetosPEM.leerObjetoPEM(fichCertificadoCA);
		X509CertificateHolder certUsu = (X509CertificateHolder) GestionObjetosPEM.leerObjetoPEM(fichCertificadoUsu);
		if (certUsu.getNotAfter().before(new Date())) {
			return false;
		}
		SubjectPublicKeyInfo keyInfo = certCA.getSubjectPublicKeyInfo();
		RSAKeyParameters keyPublic = gc.getClavePublicaMotor(keyInfo);
		ContentVerifierProvider verifierProvider = new BcRSAContentVerifierProviderBuilder(new DefaultDigestAlgorithmIdentifierFinder()).build(keyPublic);
		return certUsu.isSignatureValid(verifierProvider);
	}	
	
	// EL ESTUDIANTE PODRÁ CODIFICAR TANTOS MÉTODOS PRIVADOS COMO CONSIDERE INTERESANTE PARA UNA MEJOR ORGANIZACIÓN DEL CÓDIGO
	
	/*
	* Método para crear el objeto BcContentSignerBuilder que se utilizará para firmar el certificado de usuario
	* @return BcContentSignerBuilder. Objeto BcContentSignerBuilder
	*/
	private BcContentSignerBuilder buildContentSignerBuilder() { 
		DefaultDigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
		DefaultSignatureAlgorithmIdentifierFinder signatureAlgorithmIdentifierFinder = new DefaultSignatureAlgorithmIdentifierFinder();
		AlgorithmIdentifier sigAlgId = signatureAlgorithmIdentifierFinder.find("SHA256withRSA");
		AlgorithmIdentifier digAlgId = digestAlgorithmIdentifierFinder.find(sigAlgId);
		return new BcRSAContentSignerBuilder(sigAlgId, digAlgId);
	}
}
