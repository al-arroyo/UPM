package p2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;

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
import org.bouncycastle.operator.ContentSigner;



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

	private GestionClaves gestorClaves = null;
	private AsymmetricCipherKeyPair asymmetricCipherKeyPair = null;
	/**
	 * Constructor de la CA. 
	 * Inicializa atributos de la CA a valores por defecto
	 */
	public CA (){
		// Distinguished Name DN. C Country, O Organization name, CN Common Name. 
		this.nombreEmisor = new X500Name ("C=ES, O=DTE, CN=CA");
		this.numSerie = BigInteger.valueOf(1);
		this.añosValidez = 1; // Son los años de validez del certificado de usuario, para la CA el valor es 4
		gestorClaves = new GestionClaves();
	}
	
	/**
	* Método que genera la parejas de claves y el certificado autofirmado de la CA.
	* @throws OperatorCreationException
	* @throws IOException 
	*/
	public void generarClavesyCertificado() throws OperatorCreationException, IOException { 
		// Generar una pareja de claves (clase GestionClaves) y guardarlas EN FORMATO PEM en los ficheros 
		// indicados por NOMBRE_FICHERO_CLAVES (añadiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// 
		// Generar un certificado autofirmado: 
		// 	1. Configurar parámetros para el certificado e instanciar objeto X509v3CertificateBuilder
		// 	2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
		//	3. Generar certificado
		//	4. Guardar el certificado en formato PEM como un fichero con extensión crt (NOMBRE_FICHERO_CRT)
		
		//COMPLETAR POR EL ESTUDIANTE
		// 1. Configurar parámetros para el certificado e instanciar objeto X509v3CertificateBuilder
		Calendar calendar = new GregorianCalendar();
		Date fechaInicio = calendar.getTime();
		calendar.add(Calendar.YEAR, añosValidez);
		Date fechaFin = calendar.getTime();
		X509v3CertificateBuilder certificado = new X509v3CertificateBuilder(nombreEmisor, numSerie, fechaInicio, fechaFin, nombreEmisor, gestorClaves.getClavePublicaSPKI(clavePublicaCA));
		// 2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
		BcContentSignerBuilder contentSignerBuilder = buildContentSignerBuilder();
		// 3. Generar certificado
		X509CertificateHolder certificadoHolder = certificado.build(contentSignerBuilder.build(asymmetricCipherKeyPair.getPrivate()));
		// 4. Guardar el certificado en formato PEM como un fichero con extensión crt (NOMBRE_FICHERO_CRT)
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.CERTIFICATE_PEM_HEADER, certificadoHolder.getEncoded(), NOMBRE_FICHERO_CRT);
		//Generar la pareja de claves
		asymmetricCipherKeyPair = gestorClaves.generarClaves(numSerie, 2048);
		clavePrivadaCA = (RSAKeyParameters)asymmetricCipherKeyPair.getPrivate();
		clavePublicaCA = (RSAKeyParameters)asymmetricCipherKeyPair.getPublic();
		// Guardar las claves en ficheros
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER, gestorClaves.getClavePrivadaPKCS8(clavePrivadaCA).getEncoded(), NOMBRE_FICHERO_CLAVES + "_pri.txt");
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER, gestorClaves.getClavePublicaSPKI(clavePublicaCA).getEncoded(), NOMBRE_FICHERO_CLAVES + "_pri.txt");	}
		
	/**
	 * Método que carga la parejas de claves
	 * @throws IOException 
	 */
	public void cargarClaves ()throws IOException{
				// Carga la pareja de claves de los ficheros indicados por NOMBRE_FICHERO_CLAVES 
				// (añadiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// No carga el certificado porque se lee de fichero cuando se necesita.
		//COMPLETAR POR EL ESTUDIANTE
		clavePrivadaCA = gestorClaves.getClavePrivadaMotor((PrivateKeyInfo)GestionObjetosPEM.leerObjetoPEM(NOMBRE_FICHERO_CLAVES + "_pri.txt"));
		clavePublicaCA = gestorClaves.getClavePublicaMotor((SubjectPublicKeyInfo)GestionObjetosPEM.leerObjetoPEM(NOMBRE_FICHERO_CLAVES + "_pu.txt"));
	}


	
	/**
	 * Método que genera el certificado de un usuario a partir de una petición de certificación
	 * @param ficheroPeticion:String. Parámetro con la petición de certificación
	 * @param ficheroCertUsu:String. Parámetro con el nombre del fichero en el que se guardará el certificado del usuario
	 * @throws IOException 
	 * @throws PKCSException 
	 * @throws OperatorCreationException
	 */
	public boolean certificarPeticion(String ficheroPeticion, String ficheroCertUsu) throws IOException, 
	OperatorCreationException, PKCSException{
		
		//  Verificar que están generadas las clave privada y pública de la CA
		//  Verificar firma del solicitante (KPSolicitante en fichero de petición) 
		//  Si la verificación es ok, se genera el certificado firmado con la clave privada de la CA
		//  Se guarda el certificado en formato PEM como un fichero con extensión crt

		//  COMPLETAR POR EL ESTUDIANTE
		PKCS10CertificationRequest peticion = (PKCS10CertificationRequest) GestionObjetosPEM
				.leerObjetoPEM(ficheroPeticion);

		X509CertificateHolder certificado = this.certificateHolder(peticion);
		if (certificado.isValidOn(new Date(System.currentTimeMillis()))) {

			GestionObjetosPEM.escribirObjetoPEM("CERTIFICATE", certificado.getEncoded(), ficheroCertUsu);
			return true;
		}
		return false;

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
	/*
	 * Método para crear el certificado de usuario a partir de la petición de certificación
	 * @param peticion: PKCS10CertificationRequest. Parámetro con la petición de certificación
	 * @return certificado: X509CertificateHolder. Certificado de usuario
	 */
	private X509CertificateHolder certificateHolder(PKCS10CertificationRequest peticion) throws OperatorCreationException, PKCSException, IOException {
		BcContentSignerBuilder signerBuilder = buildContentSignerBuilder();
		ContentSigner signer = signerBuilder.build(clavePrivadaCA);
		X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(nombreEmisor, numSerie, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365), peticion.getSubject(), peticion.getSubjectPublicKeyInfo());
		return certBuilder.build(signer);
	}
}