package com.ugfind.util;

import java.util.Random;
import java.util.UUID;

public class StringUtil {
	public static boolean isEmpty(String str){
		return str==null || str.trim().length()==0;
	}
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static String forceTrim(String str){
		return str.replace(" ", "").replace(String.valueOf((char)160), "")
				.replace(String.valueOf((char)12288), "");
	}
	
	public static String bytesToHexString(byte[] data){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < data.length; i++) {                
	        if (Integer.toHexString(0xff & data[i]).length() == 1)    
	            sb.append("0").append(Integer.toHexString(0xff & data[i]));    
	        else    
	           sb.append(Integer.toHexString(0xff & data[i]));    
	    }    
	    return sb.toString();
	}
	
	public static byte[] hexStringToBytes(String hexString){
		int len=hexString.length();
		byte[] data=new byte[len/2];
		for(int i=0;i<data.length;i++){
			String s=hexString.substring(i*2,(i+1)*2);
			data[i]=Byte.parseByte(s, 16);
		}
		
		return data;
	}
	
	public static String getRandomCode(){
		String nums="0123456789";
		Random rand=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<5;i++){
			int index=rand.nextInt(10);
			sb.append(nums.charAt(index));
		}
		return sb.toString();
		
	}
	
	public static String firstUpper(String str){
		if(isEmpty(str)){
			return str;
		}else if(str.length()==1){
			return str.toUpperCase();
		}else{
			return str.substring(0,1).toUpperCase()+str.substring(1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(firstUpper(null));
	}
	
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	
}
