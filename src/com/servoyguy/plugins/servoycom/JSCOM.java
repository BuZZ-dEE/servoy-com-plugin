package com.servoyguy.plugins.servoycom;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.jacob.activeX.ActiveXComponent;

public interface JSCOM extends Remote {

	public JSVariant call(String methodName, Object[] args) throws RemoteException;
	
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) throws RemoteException;
	
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) throws RemoteException;
	
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) throws RemoteException;
	
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) throws RemoteException;
	
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1, Object arg2) throws RemoteException;
	

	public JSVariant call(String methodName, Object arg1) throws RemoteException;
	

	public JSVariant call(String methodName) throws RemoteException;

	public void put(String key, Object value) throws RemoteException;	

	public Object get(String key) throws RemoteException;	
	
	public JSCOM getChildJSCOM(String key) throws RemoteException;
	
	public JSCOM getChildJSCOM(String key, Object[] value) throws RemoteException;
	
	public JSCOM getChildJSCOM(String key,String progID, Object[] values) throws RemoteException;

	public ActiveXComponent getActiveXComponent();
	
	public String getLastError() throws RemoteException;

	public boolean isJACOBLoaded() throws RemoteException;

	public boolean release() throws RemoteException;
	

}
