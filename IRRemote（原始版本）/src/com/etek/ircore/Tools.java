package com.etek.ircore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;



public final class Tools {

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	public static String bytesToHex(byte src) {
		StringBuilder stringBuilder = new StringBuilder("");
	
			int v = src & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		return stringBuilder.toString();
	}
	

	
	
	public static String intsToHexString(int[] src,int length) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || length<= 0) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		//	Log.v("remoteSend", "data ----> " + d[i]);
		}
		return d;
	}
	
	public static String intToString(int[] data) {
		String backData = "";
		for (int d:data){
			backData += String.valueOf(d) +",";
		}
		backData = backData.substring(0, backData.length()-1);
		return backData;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	
	public static void saveDocumnet(Context mContext, String datas,String fileName) {  
		
	      
        try {  
           
            FileOutputStream outputStream =mContext.openFileOutput(fileName,  
                    Activity.MODE_PRIVATE);  
            outputStream.write(datas.getBytes());  
            outputStream.flush();  
            outputStream.close();  
            Toast.makeText(mContext, fileName+"save successed", Toast.LENGTH_LONG).show();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }
	
	
	
	public static String shiftString( String[] datas) {  
		
	      String str="{";
        for (String data:datas){
      	  str += "\"" +data + "\",";
        }
        str += "},\n";
	      return str;
  }

	public static void saveFileToSDcard(String temp, String fileName) {
		 String filepath = Environment.getExternalStorageDirectory().getPath();
	        File file = new File(filepath,fileName);
		// TODO Auto-generated method stub
		 try {  
	           
			 FileOutputStream os = new FileOutputStream(file);//输出文件流
			 os.write(temp.getBytes());  
			 os.flush();  
			 os.close();  
	           
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}

}
