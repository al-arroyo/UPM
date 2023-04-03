package p2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.lang.model.element.Element;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Identity;
import java.security.KeyPair;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
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
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
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
		//Generar la pareja de claves
		GestionClaves gestorClaves = new GestionClaves();
		AsymmetricCipherKeyPair asymmetricCipherKeyPair = gestorClaves.generarClaves(BigInteger.valueOf(3), 2048);
		clavePrivadaCA = (RSAKeyParameters)asymmetricCipherKeyPair.getPrivate();
		clavePublicaCA = (RSAKeyParameters)asymmetricCipherKeyPair.getPublic();
		// Guardar las claves en ficheros
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PKCS8KEY_PEM_HEADER, gestorClaves
					.getClavePrivadaPKCS8(clavePrivadaCA).getEncoded(), NOMBRE_FICHERO_CLAVES + "_pri.txt");
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.PUBLICKEY_PEM_HEADER, gestorClaves
						.getClavePublicaSPKI(clavePublicaCA).getEncoded(), NOMBRE_FICHERO_CLAVES + "_pu.txt");	
		// 1. Configurar parámetros para el certificado e instanciar objeto X509v3CertificateBuilder
		Date dateStart = new Date(System.currentTimeMillis());
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.YEAR, añosValidez);
		Date dateEnd = calendar.getTime();
		X509v3CertificateBuilder certificado = new X509v3CertificateBuilder(nombreEmisor, numSerie, dateStart,
							 dateEnd, nombreEmisor, gestorClaves.getClavePublicaSPKI(clavePublicaCA));
		certificado.addExtension(Extension.basicConstraints, true, new BasicConstraints(3));
		// 2. Configurar hash para resumen y algoritmo firma (MIRAR DIAPOSITIVAS DE APOYO EN MOODLE)
		BcContentSignerBuilder contentSignerBuilder = bcContentSignerBuilder();
		// 3. Generar certificado
		X509CertificateHolder certificadoHolder = certificado.build(contentSignerBuilder.build(clavePrivadaCA));
		// 4. Guardar el certificado en formato PEM como un fichero con extensión crt (NOMBRE_FICHERO_CRT)
		GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.CERTIFICATE_PEM_HEADER, certificadoHolder.getEncoded(), NOMBRE_FICHERO_CRT);
	}
	/**
	 * Método que carga la parejas de claves
	 * @throws IOException 
	 */
	public void cargarClaves ()throws IOException{
				// Carga la pareja de claves de los ficheros indicados por NOMBRE_FICHERO_CLAVES 
				// (añadiendo al nombre las cadenas "_pri.txt" y "_pu.txt")
		// No carga el certificado porque se lee de fichero cuando se necesita.
		//COMPLETAR POR EL ESTUDIANTE
		GestionClaves gestorClaves = new GestionClaves();
		clavePrivadaCA = gestorClaves.getClavePrivadaMotor((PrivateKeyInfo)GestionObjetosPEM
							.leerObjetoPEM(NOMBRE_FICHERO_CLAVES + "_pri.txt"));
		clavePublicaCA = gestorClaves.getClavePublicaMotor((SubjectPublicKeyInfo)GestionObjetosPEM
							.leerObjetoPEM(NOMBRE_FICHERO_CLAVES + "_pu.txt"));
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
		if (clavePrivadaCA == null || clavePublicaCA == null) {
			System.out.println("No se han cargado las claves");
		}
		else{
			PKCS10CertificationRequest peticion = (PKCS10CertificationRequest) GestionObjetosPEM
					.leerObjetoPEM(ficheroPeticion);
			GestionClaves gestorClaves = new GestionClaves();
			RSAKeyParameters clavePublicaSolicitante = gestorClaves.getClavePublicaMotor(peticion.getSubjectPublicKeyInfo());
			DefaultDigestAlgorithmIdentifierFinder dIdFinder = new DefaultDigestAlgorithmIdentifierFinder();
			ContentVerifierProvider contentVerifierProvider = new BcRSAContentVerifierProviderBuilder(dIdFinder)
					.build(clavePublicaSolicitante);
			if (!peticion.isSignatureValid(contentVerifierProvider)) {
				System.out.println("Firma de la petición inválida");
			}
			else{
				Date dateStart = new Date(System.currentTimeMillis());
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.add(Calendar.YEAR, añosValidez);
				Date dateEnd = calendar.getTime();
				X500Name name = new X500Name("C=ES, O=DTE, CN=Alvaro");
				X509v3CertificateBuilder certificado = new X509v3CertificateBuilder(nombreEmisor, numSerie, dateStart, dateEnd, name,
						gestorClaves.getClavePublicaSPKI(clavePublicaCA));
				BcContentSignerBuilder contentSignerBuilder = bcContentSignerBuilder();
				X509CertificateHolder certificadoFirmado = certificado.build(contentSignerBuilder.build(clavePrivadaCA));
				GestionObjetosPEM.escribirObjetoPEM(GestionObjetosPEM.CERTIFICATE_PEM_HEADER, certificadoFirmado.getEncoded(), ficheroCertUsu);
				return true;
			}
		}
		return false;
	}
	
	// EL ESTUDIANTE PODRÁ CODIFICAR TANTOS MÉTODOS PRIVADOS COMO CONSIDERE INTERESANTE PARA UNA MEJOR ORGANIZACIÓN DEL CÓDIGO

	/*
	 * Método para crear el objeto BcContentSignerBuilder que se utilizará para firmar el certificado de usuario
	 * @return BcContentSignerBuilder. Objeto BcContentSignerBuilder
	 */
	private BcContentSignerBuilder bcContentSignerBuilder() { 
		AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find("SHA256withRSA");
		AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);
		return new BcRSAContentSignerBuilder(sigAlgId, digAlgId);
	}
}