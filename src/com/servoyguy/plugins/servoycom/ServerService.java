package com.servoyguy.plugins.servoycom;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote
{
	 void registerServerCom(String progID) throws RemoteException;
}
