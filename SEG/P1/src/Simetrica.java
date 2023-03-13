/**Fichero: Simetrica.java
 * Clase para implementar la opción de simetrica del menu.
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
import java.io.IOException;
import java.security.SecureRandom;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class Simetrica {
    /**Funcion que genera una clave
     * 
     * @param file fichero que almacena la clave
     */
    public  void    keyGenerator(String file)
    {
        int size = 256;
        BufferedOutputStream output;
		try {
			output = new BufferedOutputStream(new FileOutputStream(file));
			//Crear generador de claves
			CipherKeyGenerator genKey = new CipherKeyGenerator();
			genKey.init(new KeyGenerationParameters(new SecureRandom(), size));
			//Crear clave
			byte[] key = genKey.generateKey();
			//Pasar clave a Hexadecimal
			byte[] keyH = Hex.encode(key);
			//Escribir clave en el fichero
			output.write(keyH);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**Funcion que cifra un mensaje en claro
     * con una clave y genera un fichero cifrado
     * @param fileIn fichero con mensaje en claro
     * @param fileKey fichero con la clave
     * @param fileOut fichero cifrado
     */
    public void encode(String fileIn, String fileKey, String fileOut)
    {
		try {
			BufferedReader keyput = new BufferedReader(new FileReader(fileKey));
			byte[] key = Hex.decode(keyput.readLine());
    	
			BlockCipherPadding padding = new PKCS7Padding();
			padding.init(new SecureRandom());
			PaddedBufferedBlockCipher block = new PaddedBufferedBlockCipher (new CBCBlockCipher(new TwofishEngine()), padding);
			block.init(true, new KeyParameter(key));
    	
			BufferedInputStream input = new BufferedInputStream (new FileInputStream (fileIn));
			BufferedOutputStream output = new BufferedOutputStream (new FileOutputStream(fileOut));

			byte[] dataIn = new byte[block.getBlockSize()];
			byte[] dataOut = new byte[block.getOutputSize(block.getBlockSize())];
		
			int readed = input.read(dataIn, 0, block.getBlockSize());
			int writed;
			while(readed > 0) {
				writed = block.processBytes(dataIn, 0, readed, dataOut, 0);
				readed = input.read(dataIn, 0, block.getBlockSize());
				output.write(dataOut, 0, writed);
			}
			writed = block.doFinal(dataOut, 0);
			output.write(dataOut, 0, writed);
		
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
    /**Funcion que descifra un fichero cifrado
     * 
     * @param fileIn fichero cifrado
     * @param fileKey fichero con clave
     * @param fileOut fichero descifrado
     */
    public void decode(String fileIn, String fileKey, String fileOut) {
    	try {
			BufferedReader keyput = new BufferedReader(new FileReader(fileKey));
			byte[] key = Hex.decode(keyput.readLine());
    	
			BlockCipherPadding padding = new PKCS7Padding();
			padding.init(new SecureRandom());
			PaddedBufferedBlockCipher block = new PaddedBufferedBlockCipher (new CBCBlockCipher(new TwofishEngine()), padding);
			block.init(false, new KeyParameter(key));
    	
			BufferedInputStream input = new BufferedInputStream (new FileInputStream (fileIn));
			BufferedOutputStream output = new BufferedOutputStream (new FileOutputStream(fileOut));

			byte[] dataIn = new byte[block.getBlockSize()];
			byte[] dataOut = new byte[block.getOutputSize(block.getBlockSize())];
		
			int readed = input.read(dataIn, 0, block.getBlockSize());
			int writed;
			while(readed > 0) {
				writed = block.processBytes(dataIn, 0, readed, dataOut, 0);
				readed = input.read(dataIn, 0, block.getBlockSize());
				output.write(dataOut, 0, writed);
			}
			writed = block.doFinal(dataOut, 0);
			output.write(dataOut, 0, writed);
		
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
}
