package com.servoyguy.plugins.servoycom;

import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;

public class RemoteCOM implements JSCOM {

	private final Dispatch axo;	
	private ActiveXComponent axc;
	private String lastError;
	
	public RemoteCOM(final String componentName){
		axc = null;
		try{
			axc = new ActiveXComponent(componentName);
		}
		catch(final Exception e){
			lastError = e.toString();
		}
		this.axo = (axc != null) ? axc.getObject() : null;
	}
	
	public RemoteCOM(final Dispatch _axo){
		this.axo = _axo;		
	}

	public RemoteCOM(final String componentName,final Dispatch _axo){
		this.axc = null;		
		try{
			this.axc = new ActiveXComponent(componentName);			
		}
		catch(final Exception e){
			lastError = e.toString();
		}
		this.axo = _axo;
	}
		
	@Override
	public JSVariant call(final String methodName, final Object[] args) {
		try{
			return new JSVariant(Dispatch.callN(axo, methodName, args));
		}
		catch(final Exception e){
			lastError = e.toString();
			return null;
		}
	}
	@Override
	public JSVariant call(final String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) { 
		if (axo != null) {
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
				else if(arg5 != null)
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
			catch(final Exception e){
				lastError = e.toString();
			}
		}
		return null;
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, null, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, arg7, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, arg6, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) { 
		return call(methodName, arg1, arg2, arg3, arg4, arg5, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3, Object arg4) { 
		return call(methodName, arg1, arg2, arg3, arg4, null, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1, Object arg2, Object arg3) { 
		return call(methodName, arg1, arg2, arg3, null, null, null, null, null, null, null, null, null);
	}
	public JSVariant call(String methodName, Object arg1, Object arg2) { 
		return call(methodName, arg1, arg2, null, null, null, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(String methodName, Object arg1) { 
		return call(methodName, arg1, null, null, null, null, null, null, null);
	}
	
	public JSVariant call(final String methodName) { 
		return call(methodName, null, null, null, null, null, null, null, null);
	}	
	
	public void put(final String key, final Object value) { 
		if (axo != null) {
			try{
				Dispatch.put(axo, key, value);
			}
			catch(final Exception e){
				lastError = e.toString();
			}
		}
	}
	
	public Object get(final String key) { 
		if (axo != null) {
			try{
				return Dispatch.get(axo, key);
			}
			catch(final Exception e){
				lastError = e.toString();
			}
		}
		return null;
	}
	
	public JSCOM getChildJSCOM(String key) {
		if (axo != null) {
			try{
				return new RemoteCOM(Dispatch.get(axo, key).toDispatch());
			}
			catch(final Exception e){
				lastError = e.toString();
				return null;
			}
		}
		return null;
	}
	
	public JSCOM getChildJSCOM(String key, Object[] values) {
		if (axo != null) 
		{
			try{
				return new RemoteCOM(Dispatch.call(axo, key, values).toDispatch());				
			}
			catch(final Exception e){
				lastError = e.toString();
				return null;
			}
		}
		return null;
	}

	public JSCOM getChildJSCOM(String key,String progID, Object[] values) {
		if (axo != null) 
		{
			try{
				return new RemoteCOM(progID,Dispatch.call(axo, key, values).toDispatch());				
			}
			catch(final Exception e){
				lastError = e.toString();
				return null;
			}
		}
		return null;
	}
		
	public ActiveXComponent getActiveXComponent(){
		return axc;
	}
	
	public Dispatch getDispatch(){
		return axo;
	}
	
	public String getLastError() { 
		return lastError;
	}
	
	public boolean isJACOBLoaded() { 
		return axo != null;
	}
	
	public boolean release() { 
		try{
			if (axo != null) axo.safeRelease();
			ComThread.Release();
			ComThread.quitMainSTA();
			return true;
		}
		catch(final Exception e){
			lastError = e.toString();
		}
		return false;
	}
}
