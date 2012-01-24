package com.servoyguy.plugins.servoycom;


import com.servoy.j2db.plugins.IClientPluginAccess;
import com.servoy.j2db.scripting.IScriptObject;

public class ClientScriptObject implements IScriptObject {
	
	private static final String VERSION = "1.1";
	
	private final ClientPlugin plugin;
	private String lastError;
	
	public ClientScriptObject(ClientPlugin plugin){
		this.plugin = plugin;
	}
	
	public boolean js_isJACOBInstalled(){
		return JacobUtils.isJacobInstalled();
	}

	public boolean js_installJACOB(){
		return JacobUtils.isJacobInstalled();
	}

	public JSCOM js_getNewClientJSCOM(String progID){
		if (JacobUtils.isJacobInstalled()) {
			try {
				final JSCOM myCOM = new RemoteCOM(progID);
				if (myCOM.isJACOBLoaded()) {
					return myCOM;
				} else {
					lastError = myCOM.getLastError();
					return null;
				}
			} catch (final Exception re) {
				//	Shouldn't happen
				lastError = re.getMessage();
			}
		}
		return null;
	}
	
	public JSCOM js_getNewServerJSCOM(String progID) {
		try {
			return (JSCOM) getApplication().getServerService(progID);
		} catch(final Exception re) {
			lastError = re.getMessage();
			return null;
		}
	}
	
	
	private IClientPluginAccess getApplication() {
		return plugin.getIClientPluginAccess();
	}

	public boolean js_release(final JSCOM myCOM) {
		if (myCOM != null) {
			try {
				final boolean success = myCOM.release();
				if (!success) {
					lastError = myCOM.getLastError();
				}
				return success;
			} catch(final Exception re) {
				lastError = re.getMessage();
				return false;
			}
		}
		return true;
	}

	public String js_getLastError() {
		return lastError;
	}
	
	public String js_getVersion() {
		return VERSION;
	}
	
	public String[] getParameterNames(String methodName) {
		if("helloWorld".equals(methodName))
			return new String[]{"input"};

		return null;
	}
	
	public String getSample(String methodName) {
		return null;
	}
	
	public String getToolTip(String methodName) {
		return null;
	}
	
	public boolean isDeprecated(String methodName) {
		if ("installJACOB".equals(methodName)) {
			return true;
		} else if ("getNewServerJSCOM".equals(methodName)) {
			return true;
		}
		return false;
	}
	
	public Class[] getAllReturnedTypes() {
		return new Class[] {JSCOM.class, JSVariant.class};
	}
	
}
