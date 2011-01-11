package com.servoyguy.plugins.servoycom;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.servoy.j2db.plugins.IServerAccess;
import com.servoy.j2db.plugins.IServerPlugin;
import com.servoy.j2db.plugins.PluginException;
import com.servoy.j2db.preference.PreferencePanel;
import com.servoy.j2db.util.Debug;

public class ServerPlugin  implements IServerPlugin{
	
//	private static Log log = LogFactory.getLog(Debug.class); 
	private IServerAccess application;
	private String lastError;
	private NativeInstaller ni;
	private Properties props = new Properties();
	
	public void registerServerCom(String progID) throws Exception {
		
		//make sure Jacob dll is loaded
		ni = new NativeInstaller();
		if(ni.loadJACOBDLL()){
			RemoteCOM myCOM = new RemoteCOM(progID);
			if(myCOM.isJACOBLoaded()){
				application.registerRMIService(progID,myCOM);
			}
		} else {
			throw new RuntimeException("Failed to Register: " + ni.getLastError());
		}
	}
	
	public PreferencePanel[] getPreferencePanels() {
		return null;
	}
	
	public Map getRequiredPropertyNames() {
		HashMap map = new HashMap();
		map.put("com.servoyguy.plugins.servoycom.serverLibraries", "Comma-separated list of server-side COM program names");
		return map;
	}
	
	public void initialize(IServerAccess serverAccess) throws PluginException {			
	    application = serverAccess;
	    String libs = (String)application.getSettings().get("com.servoyguy.plugins.servoycom.serverLibraries");
System.out.println("Loading Com libs: " + libs);	    
	    if(libs != null){
	    	
	    	String[] libArray = libs.split(",");
	    	for(int i = 0; i < libArray.length; i++){
System.out.println("Binding lib: " + libArray[i] + "...");	    		
				try
				{
					String prog = libArray[i].trim();
					registerServerCom(prog);
					System.out.println("Registered remote COM service: " + prog);
					
				}
				catch (Exception e)
				{
				    e.printStackTrace();
				}
	    	}
	    }
	}
	
	public String getLastError() {
		return lastError;
	}
	
	public Properties getProperties() {
		return props;
	}
	
	public void load() throws PluginException {
	}
	
	public void unload() throws PluginException {
	}
}
