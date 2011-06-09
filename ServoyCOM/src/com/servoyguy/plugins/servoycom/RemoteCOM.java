package com.servoyguy.plugins.servoycom;

import java.rmi.RemoteException;

import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;

public class RemoteCOM implements JSCOM{

	private String lastError;
	private Dispatch axo;
	
	public RemoteCOM(String componentName){
		try{
			ActiveXComponent axc = new ActiveXComponent(componentName);
			axo = axc.getObject();
		}
		catch(Exception e){
			lastError = e.toString();
		}
	}
	
	public RemoteCOM(Dispatch _axo){
		try{
			axo = _axo;
		}
		catch(Exception e){
			lastError = e.toString();
		}
	}
	
	@Override
	public JSVariant call(String methodName, Object[] args) throws RemoteException {
		try{
//			return new JSVariant(Dispatch.call(axo, methodName, args));
			return new JSVariant(Dispatch.callN(axo, methodName, args));
		}
		catch(Exception e){
			lastError = e.toString();
			return null;
		}
	}
	@Override
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) throws RemoteException { 
		try{
			if(arg12 != null)
				return new JSVariant(Dispatch.call(axo, methodName, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12}));
			if(arg11 != null)
				return new JSVariant(Dispatch.call(axo, methodName, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11}));
			if(arg10 != null)
				return new JSVariant(Dispatch.call(axo, methodName, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10}));
			if(arg9 != null)
				return new JSVariant(Dispatch.call(axo, methodName, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9}));
			if(arg8 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8));
			else if(arg7 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7));
			else if(arg6 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3, arg4, arg5, arg6));
			else if(arg6 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3, arg4, arg5));
			else if(arg4 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3, arg4));
			else if(arg3 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2, arg3));
			else if(arg2 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1, arg2));
			else if(arg1 != null)
				return new JSVariant(Dispatch.call(axo, methodName, arg1));
			else
				return new JSVariant(Dispatch.call(axo, methodName));
		}
		catch(Exception e){
			lastError = e.toString();
			return null;
		}
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, null, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, arg4, null, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3) throws RemoteException { 
		return call(methodName, arg1, arg2, arg3, null, null, null, null, null, null, null, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2) throws RemoteException { 
		return call(methodName, arg1, arg2, null, null, null, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1) throws RemoteException { 
		return call(methodName, arg1, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName) throws RemoteException { 
		return call(methodName);
	}

	
	public void put(String key, Object value) throws RemoteException { 
		try{
			Dispatch.put(axo, key, value);
		}
		catch(Exception e){
			lastError = e.toString();
		}
	}
	
	public Object get(String key) throws RemoteException { 
		try{
			return Dispatch.get(axo, key);
		}
		catch(Exception e){
			lastError = e.toString();
			return null;
		}
	}
	
	public JSCOM getChildJSCOM(String key) throws RemoteException {
		try{
			return new RemoteCOM(Dispatch.get(axo, key).toDispatch());
		}
		catch(Exception e){
			lastError = e.toString();
			return null;
		}
	}
	
	public String getLastError() throws RemoteException { 
		return lastError;
	}
	
	public boolean isJACOBLoaded() throws RemoteException { 
		return axo != null;
	}
	
	public boolean release() throws RemoteException { 
		try{
			axo.safeRelease();
			ComThread.Release();
			ComThread.quitMainSTA();
			axo = null;
			return true;
		}
		catch(Exception e){
			lastError = e.toString();
			return false;
		}
	}
}
