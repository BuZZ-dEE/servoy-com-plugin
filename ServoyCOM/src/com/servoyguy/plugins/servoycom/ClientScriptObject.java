package com.servoyguy.plugins.servoycom;


import java.rmi.AccessException;
import java.rmi.RemoteException;
import com.servoy.j2db.plugins.IClientPlugin;
import com.servoy.j2db.plugins.IClientPluginAccess;
import com.servoy.j2db.scripting.IScriptObject;

public class ClientScriptObject implements IScriptObject {
	
	private IClientPluginAccess application;
	private String lastError;
	private NativeInstaller ni;
	private ServerService ss;
	
	public ClientScriptObject(IClientPlugin app){
		application = ((ClientPlugin)app).getIClientPluginAccess();
		ni = new NativeInstaller();
	}
	
	public boolean js_isJACOBInstalled(){
		return ni.isJacobInstalled();
	}
	
	public boolean js_installJACOB(){
		boolean success = ni.loadJACOBDLL();
		lastError = ni.getLastError();
		return success;
	}

	public JSCOM js_getNewClientJSCOM(String progID){
		try{
			JSCOM myCOM = new RemoteCOM(progID);
			if(myCOM.isJACOBLoaded()){
				return myCOM;
			}
			else{
				lastError = myCOM.getLastError();
				return null;
			}
		}catch(RemoteException re){
			//	Shouldn't happen
			lastError = re.getMessage();
			return null;
		}
	}
	
	public JSCOM js_getNewServerJSCOM(String progID){
		try{
			return (JSCOM)application.getServerService(progID);
		}catch(Exception re){
			lastError = re.getMessage();
			return null;
		}
	}
	
	
	public boolean js_release(JSCOM myCOM){
		try{
			boolean success = myCOM.release();
			if(!success){
				lastError = myCOM.getLastError();
			}
			return success;
		}catch(Exception re){
			lastError = re.getMessage();
			return false;
		}
	}

	public String js_getLastError() {
		return lastError;
	}
	
	public String[] getParameterNames(String methodName) {
		if("helloWorld".equals(methodName))
			return new String[]{"input"};

		return null;
	}
	
	public String getSample(String arg0) {
		return null;
	}
	
	public String getToolTip(String methodName) {
		return null;
	}
	
	public boolean isDeprecated(String arg0) {
		return false;
	}
	
	public Class[] getAllReturnedTypes() {
		return new Class[]{JSCOM.class, JSVariant.class};
	}
	
	private ServerService getServerService() throws Exception{
		if(ss==null){
			ss = (ServerService) application.getServerService("com.servoyguy.plugins.servoycom");
		}
		
		return ss;
	}
}
