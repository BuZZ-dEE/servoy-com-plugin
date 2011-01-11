package com.servoyguy.plugins.servoycom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NativeInstaller {
	
	private String lastError;
	private String jacobFile;
	
	public NativeInstaller(){
		lastError = "";
		jacobFile =  "jacob-1.14.3-x86.dll"; //"jacob.dll";
	}
	
	public boolean loadDLL(String DLLName){
		try{
			System.load(System.getProperty("user.home") + "/.servoy/" + DLLName);
			return true;
		}
		catch(UnsatisfiedLinkError e){
			lastError = e.toString();
			return false;
		}
		//Load the DLL  into the runtime
		//System.load(this.getClass().getResource("resources/jacob-1.14.3-x86.dll").getFile());
		//String myFilePath = this.getClass().getResource("resources/jacob-1.14.3-x86.dll").getFile();
		//System.loadLibrary("jacob-1.14.3-x86");
	}
	
	public boolean isJacobInstalled(){
		return loadDLL(jacobFile);
	}
	
	public boolean loadJACOBDLL(){
		boolean installDLL = installFile(jacobFile);
		if(installDLL){
			boolean success = loadDLL(jacobFile);
			if(!success){
				return false;
			}
			else{
				return true; //everything was fine
			}
		}
		else{
			lastError = "Error finding DLL: " + jacobFile;
			return false;
		}
	}
	
	public boolean installFile(String DLLName){
		try{
			
			//check for .servoy directory where persistent items can be stored
			File file = new File(System.getProperty("user.home"), "/.servoy/");
	        if(!file.exists())
	            file.mkdirs();
	        
	        //check to see if DLL is already downloaded
	        File newDLL = new File(file.getAbsoluteFile() + "/" + DLLName);
	        if(!newDLL.exists())
	        {
		        //copy in the DLL to the folder
		        InputStream in = this.getClass().getResourceAsStream("resources/" + DLLName);
		        OutputStream out = new FileOutputStream(newDLL);
				IOUtil.streamAndClose(in, out);
	        }

			return true;
		}
		catch (FileNotFoundException e) {
			lastError = e.toString();
			return false;
		}
		catch (IOException e) {
			lastError = e.toString();
			return false;
		}
	}
	
	public String getLastError(){
		return lastError;
	}

}
