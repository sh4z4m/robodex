package com.robodex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * This will eventually be hidden from the source repository.
 * When the app is ready to deploy, this can be modified to 
 * work differently.
 */
public class Private {	
	public static String calculateHash(String s) {		
		if (s == null || s.length() == 0) return null;
		String key = "^[aX9Z.W-R|zo9o1}M#ebX@vqjR{7?bcg%lWj$I+[q-0=Ot58cJa7$;DtEVj;5";
		return md5(s + key);
	}
	
	private static String md5(String s) {
    	if (s == null) return null;
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            int val;
            for (int i=0; i<messageDigest.length; i++) {
                val = 0xFF & ((int)messageDigest[i]);
                if (val < 16) hexString.append("0");
                hexString.append(Integer.toHexString(val));
            }
                
            return hexString.toString();	            
        } 
        catch (NoSuchAlgorithmException ignored) {}	        
        return null;
    }
}
