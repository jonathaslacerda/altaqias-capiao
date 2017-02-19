package altaqias.ragatanga.capiao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Simple TripleDES Encrypt/Decrypt Test 
 * sha1, utf-8, no padding
 *
 * uses commons-codec-1.6 
 * javac -cp :commons-codec-1.6.jar TripleDESTest.java
 * java -cp :commons-codec-1.6.jar TripleDESTest 
 */

public class CryptUtils {

	public static void main(String[] args) throws Exception {
		System.out.println(toMD5("teste"));
		
	    String text = "textToEncrypt";
	    String codedtext = new CryptUtils().encrypt(text,"SecretKey");
	    String decodedtext = new CryptUtils().decrypt(codedtext,"SecretKey");
	 	System.out.println(codedtext + " ---> " + decodedtext);
	  }
  
	private String encrypt(String message, String secretKey) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		
		SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plainTextBytes = message.getBytes("utf-8");
	    byte[] buf = cipher.doFinal(plainTextBytes);
	    byte [] base64Bytes = Base64.encodeBase64(buf);
	    String base64EncryptedString = new String(base64Bytes);
	    
	    return base64EncryptedString;
	}

	private String decrypt(String encryptedText, String secretKey) throws Exception {
	    byte[] message = Base64.decodeBase64(encryptedText.getBytes("utf-8"));
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		
		Cipher decipher = Cipher.getInstance("DESede");
		decipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] plainText = decipher.doFinal(message);
		
		return new String(plainText, "UTF-8");
	}
	
	public static String toMD5(String text) throws NoSuchAlgorithmException{
	    try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}
}