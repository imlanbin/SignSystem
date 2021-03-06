package cn.edu.ccnu.imd.analysis.common.util;
/**
 * 本程序不可用于非法用途，若用于从事非法活动，
 * 开发者不承担任何法律责任，一切责任由使用者承担，
 * 并有权终止其程序使用权！
 * */
 

import java.security.*;


 

public class LybaoMd5Encrypt {

    /**
     * 对字符串进行MD5签名
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
	public final static String md5(String s){
	     char hexDigits[] = {
	         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
	         'e', 'f'};
	     try {
	       byte[] strTemp = s.getBytes();
	       MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	       mdTemp.update(strTemp);
	       byte[] md = mdTemp.digest();
	       int j = md.length;
	       char str[] = new char[j * 2];
	       int k = 0;
	       for (int i = 0; i < j; i++) {
	         byte byte0 = md[i];
	         str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	         str[k++] = hexDigits[byte0 & 0xf];
	         }
	         return new String(str);
	       }
	       catch (Exception e){
	         return null;
	       }
    }
    
    public static String MyMD5(String text){
    	for(int i=0;i<4;i++){
    		text=LybaoMd5Encrypt.md5(text);
    	}
    	return text;
    }

}

/**
 * 本程序不可用于非法用途，若用于从事非法活动，
 * 开发者不承担任何法律责任，一切责任由使用者承担，
 * 并有权终止其程序使用权！
 * */