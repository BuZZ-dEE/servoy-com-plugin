package com.servoyguy.plugins.servoycom;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.servoy.j2db.plugins.IServerAccess;
import com.servoy.j2db.plugins.IServerPlugin;
import com.servoy.j2db.plugins.PluginException;
import com.servoy.j2db.preference.PreferencePanel;
import com.servoy.j2db.util.Debug;

public class ServerPlugin  implements IServerPlugin{
	
	private IServerAccess application;
	private String lastError;
	
	public void registerServerCom(final String progID) throws Exception {
		//make sure Jacob dll is loaded
		if (JacobUtils.isJacobInstalled()){
			final RemoteCOM myCOM = new RemoteCOM(progID);
			if (myCOM.isJACOBLoaded()){
				application.registerRMIService(progID,myCOM);
			}
		} else {
			throw new RuntimeException("Failed to Register: check your log for Exception stack trace");
		}
	}
	
	public PreferencePanel[] getPreferencePanels() {
		return null;
	}
	
	public Map getRequiredPropertyNames() {
		final HashMap map = new HashMap();
		map.put("com.servoyguy.plugins.servoycom.serverLibraries", "Comma-separated list of server-side COM program names");
		return map;
	}
	
	public void initialize(final IServerAccess serverAccess) throws PluginException {			
	    application = serverAccess;
	    final String libs = (String)application.getSettings().get("com.servoyguy.plugins.servoycom.serverLibraries");
	    Debug.log("Loading Com libs: " + libs);	    
	    if (libs != null) {
	    	final String[] libArray = libs.split(",");
	    	for (int i = 0; i < libArray.length; i++) {
	    		Debug.log("Binding lib: " + libArray[i] + "...");	    		
				try {
					final String prog = libArray[i].trim();
					registerServerCom(prog);
					Debug.log("Registered remote COM service: " + prog);
				} catch (final Exception e) {
				    e.printStackTrace();
				}
	    	}
	    }
	}
	
	public String getLastError() {
		return lastError;
	}
	
	public Properties getProperties() {
		return null;
	}
	
	public void load() throws PluginException {
	}
	
	public void unload() throws PluginException {
	}
}
