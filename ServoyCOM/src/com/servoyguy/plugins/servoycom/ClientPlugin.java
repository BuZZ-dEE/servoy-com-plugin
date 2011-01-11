package com.servoyguy.plugins.servoycom;

import java.beans.PropertyChangeEvent;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.servoy.j2db.plugins.IClientPlugin;
import com.servoy.j2db.plugins.IClientPluginAccess;
import com.servoy.j2db.plugins.PluginException;
import com.servoy.j2db.preference.PreferencePanel;
import com.servoy.j2db.scripting.IScriptObject;

public class ClientPlugin  implements IClientPlugin{
	private static final String PLUGIN_NAME = "servoyguy_servoycom";

	private IClientPluginAccess application;
	private IScriptObject iso;
	
	public void initialize(IClientPluginAccess app) throws PluginException {
		application = app;
	}
	
	IClientPluginAccess getIClientPluginAccess()
	{
		return application;
	}
	
	public Icon getImage() {
		//return null;
		java.net.URL iconUrl = this.getClass().getResource("images/dll.gif"); //$NON-NLS-1$
		if (iconUrl != null)
		{
			return new ImageIcon(iconUrl);
		}
		else
		{
			return null;
		}
	}

	public String getName() {
		return PLUGIN_NAME;
	}

	public PreferencePanel[] getPreferencePanels() {
		return null;
	}

	public IScriptObject getScriptObject() {
		if(iso == null){
			iso = new ClientScriptObject(this);
		}
		
		return iso;
	}

	public Properties getProperties() {
		return null;
	}

	public void load() throws PluginException {
	}

	public void unload() throws PluginException {
		application = null;
	}

	public void propertyChange(PropertyChangeEvent arg0) {
	}
}
