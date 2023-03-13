/**Fichero: Asimetrica.java
 * Clase para implementar la opción de asimetrica del menu.
 * Asignatura: SEG
 * @author Alvaro Miguel Arroyo Gonzalez
 * @version 1.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.encoders.Hex;





public class Asimetrica {
    
	/**Función que genera un par de claves: privada y publica
	 * pasandole por parametro el nombre de los ficheros
	 * donde van a almacenarlas
	 * @param filePrivate fichero donde se almacena la clave privada
	 * @param filePublic fichero donde se almacena la clave publica
	 */
	public  void    keyGenerator(String filePrivate, String filePublic)
    {
		RSAKeyPairGenerator gen = new RSAKeyPairGenerator();
		gen.init(new RSAKeyGenerationParameters(BigInteger.valueOf(17), new SecureRandom(), 1024, 10));
        
		AsymmetricCipherKeyPair keys = gen.generateKeyPair();
		RSAKeyParameters keyPublic = (RSAKeyParameters) keys.getPublic();
		RSAKeyParameters keyPrivate = (RSAKeyParameters) keys.getPrivate();

		try (PrintWriter pb = new PrintWriter(new FileWriter(filePublic));
		     PrintWriter pr = new PrintWriter(new FileWriter(filePrivate))) {

		    pb.println(Hex.toHexString(keyPublic.getModulus().toByteArray()));
		    pb.println(Hex.toHexString(keyPublic.getExponent().toByteArray()));

		    pr.println(Hex.toHexString(keyPrivate.getModulus().toByteArray()));
		    pr.println(Hex.toHexString(keyPrivate.getExponent().toByteArray()));

		    new GuardarFormatoPEM().guardarClavesPEM(keyPublic, keyPrivate);

		} catch (IOException e) {
		    e.printStackTrace();
		}

    }
	/**Función que cifra un fichero de entrada que sera
	 * el mensaje en claro, en funcion de si se ha elegido
	 * cifrar con la clave publica o privada 
	 * @param p boolean para indicar si se usa clave publica o privada
	 * @param fileIn nombre del fichero en claro
	 * @param fileKey nombre del fichero con la clave
	 * @param fileOut nombre del fichero cifrado
	 */
    public void encode(boolean p, String fileIn, 
    					String fileKey, String fileOut) {
		try {
			BufferedReader keyput = new BufferedReader(new FileReader(fileKey));
			
			byte[] keyUnit = Hex.decode(keyput.readLine());
			byte[] keyExponent = Hex.decode(keyput.readLine());
    	
			BigInteger unit = new BigInteger(keyUnit);
			BigInteger exponent = new BigInteger(keyExponent);
			
			AsymmetricBlockCipher block = new PKCS1Encoding(new RSAEngine());
			block.init(true, new RSAKeyParameters(p, unit, exponent));
    	
			BufferedInputStream input = new BufferedInputStream (new FileInputStream (fileIn));
			BufferedOutputStream output = new BufferedOutputStream (new FileOutputStream(fileOut));

			byte[] dataIn = new byte[block.getInputBlockSize()];
			int readed = input.read(dataIn, 0, block.getInputBlockSize());
			while(readed > 0) {
				byte[] dataOut = block.processBlock(dataIn, 0, readed);
				readed = input.read(dataIn, 0, block.getInputBlockSize());
				output.write(dataOut);
			}
		
			keyput.close();
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**Función que descifra un fichero de entrada que estara
	 * cifrado, en funcion de si se ha elegido
	 * descifrar con la clave publica o privada 
	 * @param p boolean para indicar si se usa clave publica o privada
	 * @param fileIn nombre del fichero cifrado
	 * @param fileKey nombre del fichero con la clave
	 * @param fileOut nombre del fichero descifrado
	 */
    public void decode(boolean p, String fileIn, String fileKey, String fileOut) {
    	try {
    		BufferedReader keyput = new BufferedReader(new FileReader(fileKey));
			
			byte[] keyUnit = Hex.decode(keyput.readLine());
			byte[] keyExponent = Hex.decode(keyput.readLine());
    	
			BigInteger unit = new BigInteger(keyUnit);
			BigInteger exponent = new BigInteger(keyExponent);
			
			AsymmetricBlockCipher block = new PKCS1Encoding(new RSAEngine());
			block.init(false, new RSAKeyParameters(p, unit, exponent));
    	
			BufferedInputStream input = new BufferedInputStream (new FileInputStream (fileIn));
			BufferedOutputStream output = new BufferedOutputStream (new FileOutputStream(fileOut));

			byte[] dataIn = new byte[block.getInputBlockSize()];
			int readed = input.read(dataIn, 0, block.getInputBlockSize());
			while(readed > 0) {
				byte[] dataOut = block.processBlock(dataIn, 0, readed);
				readed = input.read(dataIn, 0, block.getInputBlockSize());
				output.write(dataOut);
			}
			keyput.close();
			input.close();
			output.close();
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**Funcion que firma un fichero , 
     * genera un resumen que cifrara 
     * con la clave privada
     * y genera un fichero firmado
     * @param fileKey fichero con la clave privada
     * @param fileClear fichero con el mensaje en claro
     * @param fileSignature fichero resultante firmado
     */
    public void signature(String fileKey, String fileClear, String fileSignature){
		
		try {	
			BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(fileClear));
			BufferedOutputStream hashFile = new BufferedOutputStream(new FileOutputStream("hash.txt"));
			Digest hash = new SHA256Digest();
		
			byte[] dataIn = new byte[hash.getDigestSize()];
			byte[] dataOut = new byte[hash.getDigestSize()];
			int readed = inFile.read(dataIn);
			
			while (readed > 0) {
				hash.update(dataIn, 0, dataIn.length);
				readed = inFile.read(dataIn);
			}
			hash.doFinal(dataOut, 0);
			hashFile.write(dataOut);
			inFile.close();
			hashFile.close();
			encode(true, "hash.txt", fileKey, fileSignature);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**Funcion que verifica la firma
     * genera otro resumen con el mensaje en claro
     * descifra el resumen recibido
     * compara el resumen autogenerado con el recibido
     * @param fileKey fichero con la clave publica
     * @param fileClear fichero con el mensaje en claro
     * @param fileHash fichero firmado
     * @return true si es correcto o false si no lo es
     */
	public boolean checkSignature(String fileKey, String fileClear, String fileHash){
		
		boolean check = false;

		try {	
			BufferedInputStream inBuffer = new BufferedInputStream(new FileInputStream(fileClear));
			Digest hash = new SHA256Digest();
			byte[] dataIn = new byte[hash.getDigestSize()];
			byte[] dataHash = new byte[hash.getDigestSize()];
			int readed = inBuffer.read(dataIn);
			while (readed > 0) {
				hash.update(dataIn, 0, dataIn.length);
				readed = inBuffer.read(dataIn);
			}
			hash.doFinal(dataHash, 0);
			inBuffer.close();
			decode(false, fileHash, fileKey, "decodeHash.txt");
			inBuffer = new BufferedInputStream(new FileInputStream("decodeHash.txt"));
			byte[] hashOut = new byte[hash.getDigestSize()];
			inBuffer.read(hashOut);
			check = Arrays.equals(dataHash, hashOut);
			inBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return check;
	}
}
