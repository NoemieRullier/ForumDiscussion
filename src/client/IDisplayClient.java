package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDisplayClient extends Remote {

	/*
	 * Display the message msg on this windows
	 */
	public void display (String msg) throws RemoteException;
}
