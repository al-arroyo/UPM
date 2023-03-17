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
	
	private GestionClaves gc;

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
	* Método que genera la parejas de claves y el certificado autofirmado de la CA.
	* @throws OperatorCreationException
	* @throws IOException 
	*/
	public void generarClavesyCertificado()throws OperatorCreationException, IOException {
		// Generar una pareja de claves (clase GestionClaves) y guardarlas EN FORMATO PEM en los ficheros 
                // indicados por NOMBRE_FICHERO_CLAVES (añadiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// 
		// Generar un certificado autofirmado: 
		// 	1. Configurar parámetros para el certificado e instanciar objeto X509v3CertificateBuilder
		// 	2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
		//	3. Generar certificado
		//	4. Guardar el certificado en formato PEM como un fichero con extensión crt (NOMBRE_FICHERO_CRT)
		//COMPLETAR POR EL ESTUDIANTE


	// 	1. Configurar parámetros para el certificado e instanciar objeto X509v3CertificateBuilder
	Calendar endCertificate = GregorianCalendar.getInstance();
	endCertificate.add(Calendar.Year, añosValidez);
	Date endDateC = endCertificate.getTime();

	X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(
		issuer,
		serial,
		notBefore,
		notAfter,
		subject,
		SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()));
	// 	2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
	BcContentSignerBuilder csBuilder = builderFirma();
	//	3. Generar certificado
	X509CertificateHolder holder = certBldr.build(csBuilder.build(this.clavePrivadaCA));
	// 4. Guardar el certificado en formato PEM como un fichero con extensión crt
	// (NOMBRE_FICHERO_CRT)
	GestionObjetosPEM.escribirObjetoPEM("CERTIFICADO", holder.getEncoded(), NOMBRE_FICHERO_CRT);
	
	//Cosas de chatgpt
	// Generate a key pair and save the keys in PEM format
    GestionClaves gc = new GestionClaves();
    KeyPair keyPair = gc.generarClaves();
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
	 * Método que carga la parejas de claves
	 * @throws IOException 
	 */
	public void cargarClaves (throws IOException{
                // Carga la pareja de claves de los ficheros indicados por NOMBRE_FICHERO_CLAVES 
                // (añadiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// No carga el certificado porque se lee de fichero cuando se necesita.
		
		GestionClaves gc = new GestionClaves(); // Clase con métodos para manejar las claves
		//COMPLETAR POR EL ESTUDIANTE



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



	
	}
	
	// EL ESTUDIANTE PODRÁ CODIFICAR TANTOS MÉTODOS PRIVADOS COMO CONSIDERE INTERESANTE PARA UNA MEJOR ORGANIZACIÓN DEL CÓDIGO
	
	/**
	 * Método privado que comprueba la validez de la firma de una petición de
	 * certificación.
	 * 
	 * @param peticion.     Parámetro con la petición de certificación en formato
	 *                      PKCS10
	 * @param clavePublica. Parámetro con la clave pública
	 * @return boolean. true si verificación firma, false en caso contrario.
	 */
	private boolean verificaFirmaDePeticion(PKCS10CertificationRequest peticion, RSAKeyParameters clavePublica)
	throws OperatorCreationException, PKCSException {
		
		boolean verificado = false;
		DefaultDigestAlgorithmIdentifierFinder digAF = new DefaultDigestAlgorithmIdentifierFinder();
		if (peticion.isSignatureValid(new BcRSAContentVerifierProviderBuilder(digAF).build(clavePublica)))
		verificado = true;
		
		return verificado;
	}
	
	/**
	 * Método privado que genera el certificado de un usuario a partir de una
	 * petición de certificación Este método es necesario emplearlo para Certificar
	 * una Petición
	 * 
	 * @param pet:PKCS10CertificationRequest. Parámetro con la petición de
	 *                                        certificación en formato PKCS10
	 * @throws PKCSException
	 * @throws OperatorCreationException
	 * @throws IOException
	 * @result X509CertificateHolder: certificado X.509
	 */
	private X509CertificateHolder crearCertificado(PKCS10CertificationRequest peticion)
	throws OperatorCreationException, PKCSException, IOException {
		
		X509CertificateHolder crtHolder = null;
		
		SubjectPublicKeyInfo clavePublicaES = peticion.getSubjectPublicKeyInfo();
		RSAKeyParameters clavePublicaEntidad = gc.getClavePublicaMotor(clavePublicaES);
		
		if (this.verificaFirmaDePeticion(peticion, clavePublicaEntidad)) {
			
			X500Name nombreSolicitante = peticion.getSubject();
			
			Calendar finCer = GregorianCalendar.getInstance();
			finCer.add(Calendar.YEAR, añosValidez);
			Date fFCer = finCer.getTime();
			
			X509v3CertificateBuilder certBldr = new X509v3CertificateBuilder(nombreEmisor, numSerie,
			new Date(System.currentTimeMillis()), fFCer, nombreSolicitante, clavePublicaES);
			
			DefaultSignatureAlgorithmIdentifierFinder sigAF = new DefaultSignatureAlgorithmIdentifierFinder();
			DefaultDigestAlgorithmIdentifierFinder digAF = new DefaultDigestAlgorithmIdentifierFinder();
			
			AlgorithmIdentifier sigAI = sigAF.find("SHA256withRSA");
			AlgorithmIdentifier digAI = digAF.find(sigAI);
			
			BcContentSignerBuilder csBuilder = new BcRSAContentSignerBuilder(sigAI, digAI);
			
			crtHolder = certBldr.build(csBuilder.build(this.clavePrivadaCA));
		}
		
		return crtHolder;
	}
	
	/**
	 * Método que comprueba si ya se han generado las claves.
	 * 
	 * @return boolean. true si ya se habían generado, false en otro caso.
	 */
	private boolean clavesGeneradas() {
		
		boolean generadas = false;
		if ((clavePrivadaCA != null) && (clavePublicaCA != null))
		
		generadas = true;
		
		return generadas;
	}
	/**
	 * Método para guardar las claves.
	 * 
	 * @throws IOException
	 */
			
	private void guardarClaves() throws IOException {
			
		AsymmetricCipherKeyPair claves = gc.generarClaves(BigInteger.valueOf(3), 2048);
				
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER,
		gc.getClavePublicaSPKI(claves.getPublic()).getEncoded(), NOMBRE_FICHERO_CLAVES + "-public");
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PKCS8KEY_PEM_HEADER,
		gc.getClavePrivadaPKCS8(claves.getPrivate()).getEncoded(), NOMBRE_FICHERO_CLAVES + "-private");
			
		clavePrivadaCA = (RSAKeyParameters) claves.getPrivate();
		clavePublicaCA = (RSAKeyParameters) claves.getPublic();
	}
			
	/**
	 * Método que configura e instancia un builder para la firma.
	 * 
	 * @return csBuilder
	 */
		
	private BcContentSignerBuilder builderFirma() {
		DefaultSignatureAlgorithmIdentifierFinder sigAlgFinder = new DefaultSignatureAlgorithmIdentifierFinder();
		DefaultDigestAlgorithmIdentifierFinder digAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();
		AlgorithmIdentifier sigAlgId = sigAlgFinder.find("SHA256withRSA");
		AlgorithmIdentifier digAlgId = digAlgFinder.find(sigAlgId);
		BcContentSignerBuilder csBuilder = new BcRSAContentSignerBuilder(sigAlgId, digAlgId);
		return csBuilder;
	}		
}