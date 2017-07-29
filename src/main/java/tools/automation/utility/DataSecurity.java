package tools.automation.utility;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DataSecurity {
	
	private byte[] sharedvector = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11 };
	private String key = "toolsgroupautomation";
	
	public String encryptTextURL(String text){
		/*try {
			return URLEncoder.encode(  encryptText( text )  , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;*/
		return text;
	}
	
	public String decryptTextURL(String text){
		/*try {
			
			
			return decryptText( URLDecoder.decode( text ,  "UTF-8"   ) );
			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;*/
		return text;
	}
	
	
	public String encryptText(String RawText)
	{
		String EncText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		
		
		byte[] toEncryptArray = null;

		try
		{

			toEncryptArray =  RawText.getBytes("UTF-8");        
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));

			if(temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for(int i=temporaryKey.length;i< 24;i++)
				{                   
					keyArray[i] =  temporaryKey[index];
				}
			}        

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");            
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
			byte[] encrypted = c.doFinal(toEncryptArray);            
			EncText = new String( Base64.encodeBase64(encrypted) );

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return EncText;        
	}

	public String decryptText(String EncText)
	{

		String RawText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;
		
		
		try
		{
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key.getBytes("UTF-8"));           

			if(temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for(int i=temporaryKey.length;i< 24;i++)
				{                  
					keyArray[i] =  temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
			byte[] decrypted = c.doFinal(Base64.decodeBase64(EncText.getBytes()));   

			RawText = new String(decrypted, "UTF-8");                    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}      

		return RawText; 

	}

}
