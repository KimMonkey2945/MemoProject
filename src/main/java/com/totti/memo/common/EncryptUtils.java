package com.totti.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	//암호화 메소드
	public static String md5(String message) {
		String encData = "";
		try {
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		//asdf
		//[000000000, 00000000, 00000000, 00000000]
		byte[] bytes = message.getBytes();
		md.update(bytes);
		
		byte[] digest = md.digest();
		
		//숫자 -> 문자열
		for(int i = 0; i < digest.length; i++) {
			encData += Integer.toHexString(digest[i] & 0xff); 
		}
		
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		return encData;
		
		
	}
	
	
}
