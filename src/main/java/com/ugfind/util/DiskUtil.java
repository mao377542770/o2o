package com.ugfind.util;

import java.io.File;  
import java.io.FileWriter;  
import java.io.BufferedReader;  
import java.io.InputStreamReader;  

public class DiskUtil{
		//获得硬盘号
		//drive : 盘符
	    private String getSerialNumber(String drive) { 
	        String result = null;
	        try {  
	            File file = File.createTempFile("damn", ".vbs");  
	            file.deleteOnExit();  
	            FileWriter fw = new java.io.FileWriter(file);  
	            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"  
	                    + "Set colDrives = objFSO.Drives\n"  
	                    + "Set objDrive = colDrives.item(\""  
	                    + drive  
	                    + "\")\n"  
	                    + "Wscript.Echo objDrive.SerialNumber"; // see note  
	            fw.write(vbs);  
	            fw.close();  
	            Process p = Runtime.getRuntime().exec(  
	                    "cscript //NoLogo " + file.getPath());  
	            BufferedReader input = new BufferedReader(new InputStreamReader(  
	                    p.getInputStream()));  
	            String line;  
	            while ((line = input.readLine()) != null) {  
	                result += line;  
	            }  
	            input.close();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return result.trim();  
	    }
	    
	    //获得该计算机唯一秘钥
	   private String getUgPassKey(){
		   String key = null;
	       String diskKey = this.getSerialNumber("C");
	       if(diskKey != null){
	    	   key = MD5Util.string2MD5("ug@"+diskKey+"find");
	       }
		   return key;
	    }
}
