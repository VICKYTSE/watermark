package com.cumt.watermark.utility.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class md5_key {
	private static final String defaultCharset = "UTF-8";
	private static final String KEY_AES = "AES";
	private static final String KEY_MD5 = "MD5";
    
	//java�Դ���MessageDigestʵ��md5�����㷨
	private static MessageDigest md5Digest;
	static {
		try {
			md5Digest = MessageDigest.getInstance(KEY_MD5);
		    } 	catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		}
	
	//����
	public  static String encrypt(String data, String key) {
		return doAES(data, key, Cipher.ENCRYPT_MODE);
		}
	//����
	public static String decrypt(String data, String key) {
		return doAES(data, key, Cipher.DECRYPT_MODE);
		}
 
	private static String doAES(String data, String key, int mode) {
		try {
			boolean encrypt = mode == Cipher.ENCRYPT_MODE;
			byte[] content;
			if (encrypt) 
			{
				content = data.getBytes(defaultCharset);
			} 
			else 
			{
				content = parseHexStr2Byte(data);
			}
			SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(defaultCharset)), KEY_AES);    //������Կ
			Cipher cipher = Cipher.getInstance(KEY_AES);    //����������
			cipher.init(mode, keySpec);    //��ʼ��
			byte[] result = cipher.doFinal(content);    //���ܻ����
			if (encrypt) {
				return parseByte2HexStr(result);
			} else {
				return new String(result, defaultCharset);
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
		}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
		}
}


