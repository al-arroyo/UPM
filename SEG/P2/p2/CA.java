package p2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.math.BigInteger;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import java.util.Date;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcContentSignerBuilder;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.bc.BcRSAContentVerifierProviderBuilder;


/**
* Esta clase implementa el comportamiento de una CA
* @author Seg Red Ser
* @version 1.0
*/
public class CA {
	
	private final X500Name nombreEmisor;
	private BigInteger numSerie;
	private final int añosValidez; 
	
	public final static String NOMBRE_FICHERO_CRT = "CertificadoCA.crt";
	public final static String NOMBRE_FICHERO_CLAVES = "CA-claves";
	
	private RSAKeyParameters clavePrivadaCA = null;
	private RSAKeyParameters clavePublicaCA = null;
	
	/**
	 * Constructor de la CA. 
	 * Inicializa atributos de la CA a valores por defecto
	 */
	public CA (){
		// Distinguished Name DN. C Country, O Organization name, CN Common Name. 
		this.nombreEmisor = new X500Name ("C=ES, O=DTE, CN=CA");
		this.numSerie = BigInteger.valueOf(1);
		this.añosValidez = 1; // Son los años de validez del certificado de usuario, para la CA el valor es 4
	}
	
	/**
	* M�todo que genera la parejas de claves y el certificado autofirmado de la CA.
	* @throws OperatorCreationException
	* @throws IOException 
	*/
	public void generarClavesyCertificado()throws OperatorCreationException, IOException {
		// Generar una pareja de claves (clase GestionClaves) y guardarlas EN FORMATO PEM en los ficheros 
                // indicados por NOMBRE_FICHERO_CLAVES (a�adiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// 
		// Generar un certificado autofirmado: 
		// 	1. Configurar par�metros para el certificado e instanciar objeto X509v3CertificateBuilder
		// 	2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
		//	3. Generar certificado
		//	4. Guardar el certificado en formato PEM como un fichero con extensi�n crt (NOMBRE_FICHERO_CRT)
		//COMPLETAR POR EL ESTUDIANTE

	// Generate a key pair and save the keys in PEM format
    GestionClaves gc = new GestionClaves();
    KeyPair keyPair = gc.generarParClaves();
    String keyFileNamePrefix = "NOMBRE_FICHERO_CLAVES";
    guardarClaveEnFormatoPEM(keyPair.getPublic(), keyFileNamePrefix + "_pu.txt");
    guardarClaveEnFormatoPEM(keyPair.getPrivate(), keyFileNamePrefix + "_pri.txt");

    // Generate a self-signed X.509 certificate
    X500Name issuer = new X500Name("CN=Self-Signed Certificate");
    X500Name subject = issuer; // Self-signed
    BigInteger serial = new BigInteger(64, new SecureRandom());
    Date notBefore = new Date(System.currentTimeMillis(- 1000L * 60 * 60 * 24 * 30); // 30 days before now
    Date notAfter = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365); // 1 year after now
    X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(
            issuer,
            serial,
            notBefore,
            notAfter,
            subject,
            SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded())
    );
    AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find("SHA256withRSA");
    AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);
    ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
    X509CertificateHolder certHolder = certBuilder.build(signer);
    X509Certificate cert = new JcaX509CertificateConverter().getCertificate(certHolder);

    // Save the certificate in PEM format
	}




	/**
	 * M�todo que carga la parejas de claves
	 * @throws IOException 
	 */
	public void cargarClaves (throws IOException{
                // Carga la pareja de claves de los ficheros indicados por NOMBRE_FICHERO_CLAVES 
                // (a�adiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// No carga el certificado porque se lee de fichero cuando se necesita.
		
		GestionClaves gc = new GestionClaves(); // Clase con m�todos para manejar las claves
		//COMPLETAR POR EL ESTUDIANTE



	}


	
	/**
	 * Método que genera el certificado de un usuario a partir de una petici�n de certificaci�n
	 * @param ficheroPeticion:String. Par�metro con la petici�n de certificaci�n
	 * @param ficheroCertUsu:String. Par�metro con el nombre del fichero en el que se guardar� el certificado del usuario
	 * @throws IOException 
	 * @throws PKCSException 
	 * @throws OperatorCreationException
	 */
	public boolean certificarPeticion(String ficheroPeticion, String ficheroCertUsu) throws IOException, 
	OperatorCreationException, PKCSException{
		
		//  Verificar que est�n generadas las clave privada y p�blica de la CA
		//  Verificar firma del solicitante (KPSolicitante en fichero de petici�n) 
		//  Si la verificaci�n es ok, se genera el certificado firmado con la clave privada de la CA
		//  Se guarda el certificado en formato PEM como un fichero con extensi�n crt

		//  COMPLETAR POR EL ESTUDIANTE



	
	}
	
}
	// EL ESTUDIANTE PODR� CODIFICAR TANTOS M�TODOS PRIVADOS COMO CONSIDERE INTERESANTE PARA UNA MEJOR ORGANIZACI�N DEL C�DIGO